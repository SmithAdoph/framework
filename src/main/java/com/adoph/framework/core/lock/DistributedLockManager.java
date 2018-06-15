package com.adoph.framework.core.lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
//import redis.clients.jedis.Protocol;
//import redis.clients.util.SafeEncoder;

import javax.annotation.Resource;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 基于Redis实现的分布式锁（DLM）
 * 分布式锁特性：
 * 1.互斥性（在任何特定时刻，只有一个客户端可以锁定）
 * 2.安全性（避免死锁。即使锁定资源的客户端崩溃或被分区，也总是可以获取锁）
 * 3.容错性（只要大多数Redis节点启动，客户端就可以获取并释放锁）
 * <br/>
 * 存在问题：
 * 1.客户端c1获取到锁key1，在key1过期后自动释放后(c1依然正常执行完他的业务代码！！！)，客户端c2可以获取同一把锁key1
 *
 * @author Adoph
 * @version v1.0
 * @date 2018/6/11
 */
@Component
public class DistributedLockManager {

    private Logger logger = LoggerFactory.getLogger(DistributedLockManager.class);

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 'set' command
     */
    private static final String REDIS_SET_COMMAND = "SET";

    /**
     * 默认超时时间5秒
     */
    private static final int DEFAULT_TIMEOUT = 5;

    /**
     * 默认随机等待时间边界50~150毫秒
     */
    private static final int DEFAULT_RANDOM_BOUND = 100;

    /**
     * 尝试获取锁
     *
     * @param key        key
     * @param clientId   客户端id
     * @param expireTime 锁有效时间(秒)
     * @param timeout    获取锁等待时间(秒)
     * @return boolean
     */
    public boolean tryLock(String key, String clientId, int expireTime, int timeout) {
        logger.info("开始获取锁，锁信息[key={}, clientId={}, expireTime={}秒, timeout={}秒]", key, clientId, expireTime, timeout);
        long start = System.currentTimeMillis();
        boolean isLocked;
        do {
            isLocked = getLock(key, clientId, expireTime);
            try {
                if (!isLocked) {
                    //当客户端无法获得锁定时，应该在随机延迟之后再次尝试
                    //避免以试图同时尝试同时尝试获取同一资源的锁定的多个客户端都未取胜
                    TimeUnit.MILLISECONDS.sleep(new Random().nextInt(DEFAULT_RANDOM_BOUND) + 50);
                }
            } catch (InterruptedException e) {
                logger.error("获取锁异常，锁信息[key=" + key + ", clientId=" + clientId + ", expireTime=" + expireTime + "秒, timeout=" + timeout + "秒]！", e);
            }
        } while (!isLocked && (System.currentTimeMillis() - start) / 1000 < timeout);
        logger.info("获取锁{}，消耗时间{}毫秒！锁信息[key={}, clientId={}, expireTime={}秒, timeout={}秒]",
                isLocked ? "成功" : "失败", now() - start, key, clientId, expireTime, timeout);
        return isLocked;
    }

    /**
     * 获取锁
     * 默认超时时间5秒
     *
     * @param key        key
     * @param clientId   客户端id
     * @param expireTime 锁有效时间
     * @return boolean
     */
    public boolean lock(String key, String clientId, int expireTime) {
        return tryLock(key, clientId, expireTime, DEFAULT_TIMEOUT);
    }


    /**
     * 获取锁(1)，不建议使用
     * 为了解决锁超时继续执行的问题，存在问题：<br/>
     * 1.必须等待业务代码执行完才能判断是否超时！
     * 2.执行和回滚在两个方法中，回滚麻烦！
     *
     * @param key        key
     * @param clientId   客户端id
     * @param expireTime 锁过期时间
     * @param action     用于获取锁成功或失败后的具体行为
     */
    @Deprecated
    public void lock(String key, String clientId, int expireTime, LockAction action) {
        boolean locked = tryLock(key, clientId, expireTime, DEFAULT_TIMEOUT);
        if (locked) {
            long startExecTime = now();
            action.execute(true);
            long execTime = now() - startExecTime;
            if (execTime > expireTime * 1000) {//执行业务时间大于锁过期时间，回滚事务
                action.rollback();
            }
        } else {
            action.execute(false);
        }
    }

    /**
     * 获取锁
     *
     * @param key        key
     * @param clientId   客户端id(建议：一个随机值或者uuid)
     * @param expireTime 锁有效时间(秒)
     * @return boolean
     */
    public boolean getLock(String key, String clientId, int expireTime) {
        return set(key, clientId, "NX", "EX", expireTime);
    }

    /**
     * 释放锁
     * 备注：通过执行Redis内置的Lua解释器，可以使用EVAL命令对Lua脚本进行求值
     * example: <code>EVAL 'if redis.call("get",KEYS[1]) == ARGV[1] then return redis.call("del",KEYS[1]) else return 0 end' 1 key value</code>
     *
     * @param key      key
     * @param clientId 客户端id
     * @return boolean
     */
    public boolean unlock(String key, String clientId) {
        String script = "if redis.call(\"get\",KEYS[1]) == ARGV[1] then return redis.call(\"del\",KEYS[1]) else return 0 end";
        Long r = stringRedisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.eval(script.getBytes(),
                        ReturnType.INTEGER,
                        1,
                        key.getBytes(),
                        clientId.getBytes());
            }
        });
        logger.info("释放锁{}！锁信息[key={}, clientId={}]", r == 1L ? "成功" : "失败", key, clientId);
        return r == 1L;
    }

    /**
     * SET key value [EX seconds] [PX milliseconds] [NX|XX] <br/>
     * EX seconds -- Set the specified expire time, in seconds. <br/>
     * PX milliseconds -- Set the specified expire time, in milliseconds. <br/>
     * NX -- Only set the key if it does not already exist. <br/>
     * XX -- Only set the key if it already exist. <br/>
     * example: SET test xxoo NX EX 10 <br/>
     *
     * @param key        key
     * @param value      值
     * @param nxxx       NX/XX
     * @param expx       EX/px
     * @param expireTime 过期时间(秒)
     */
    private boolean set(String key, String value, String nxxx, String expx, int expireTime) {
        Object o = stringRedisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.execute(REDIS_SET_COMMAND,
                        key.getBytes(),
                        value.getBytes(),
                        nxxx.getBytes(),
                        expx.getBytes(),
                        (expireTime + "").getBytes());
            }
        });
        return o != null;
    }

    /**
     * 生成一个UUID作为客户端id
     *
     * @return 一个36位长度的UUID
     */
    public String getClientId() {
        return UUID.randomUUID().toString();
    }

    private long now() {
        return System.currentTimeMillis();
    }

}
