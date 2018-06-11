package com.adoph.framework.core.lock;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Protocol;
import redis.clients.util.SafeEncoder;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * 基于Redis实现的分布式锁（DLM）
 * 分布式锁特性：
 * 1.互斥性（在任何特定时刻，只有一个客户端可以锁定）
 * 2.安全性（避免死锁。即使锁定资源的客户端崩溃或被分区，也总是可以获取锁）
 * 3.容错性（只要大多数Redis节点启动，客户端就可以获取并释放锁）
 *
 * @author Adoph
 * @version v1.0
 * @date 2018/6/11
 */
@Component
public class DistributedLockManager {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 'set' command
     */
    private static final String REDIS_SET_COMMAND = "SET";

    /**
     * 获取锁
     *
     * @param key        key
     * @param clientId   客户端id(建议：一个随机值或者uuid)
     * @param expireTime 锁有效时间(秒)
     * @return boolean
     */
    public boolean lock(String key, String clientId, int expireTime) {
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
                return connection.eval(script.getBytes(), ReturnType.INTEGER, 1, key.getBytes(), clientId.getBytes());
            }
        });
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
     * @param nxxx       NX
     * @param expx       EX
     * @param expireTime 过期时间(秒)
     */
    private boolean set(String key, String value, String nxxx, String expx, int expireTime) {
        Object o = stringRedisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.execute(REDIS_SET_COMMAND,
                        SafeEncoder.encode(key),
                        SafeEncoder.encode(value),
                        SafeEncoder.encode(nxxx),
                        SafeEncoder.encode(expx),
                        Protocol.toByteArray(expireTime));
            }
        });
        return o != null;
    }

    /**
     * 生成一个UUID作为客户端id
     *
     * @return 一个36位长度的UUID
     */
    public static String createClientId() {
        return UUID.randomUUID().toString();
    }
}
