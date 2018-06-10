package com.adoph.framework.core.lock;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Protocol;
import redis.clients.util.SafeEncoder;

import javax.annotation.Resource;

/**
 * 基于Redis实现的分布式锁
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

    private static final String SET_COMMAND = "SET";

    /**
     * 获取锁
     *
     * @param key        key
     * @param value      值(建议：客户端id，一个随机值)
     * @param expireTime 锁有效时间(秒)
     * @return boolean
     */
    public boolean lock(String key, String value, int expireTime) {
        return set(key, value, "NX", "EX", expireTime);
    }

    public boolean unlock(String key, String value) {
        return false;
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
                return connection.execute(SET_COMMAND,
                        SafeEncoder.encode(key),
                        SafeEncoder.encode(value),
                        SafeEncoder.encode(nxxx),
                        SafeEncoder.encode(expx),
                        Protocol.toByteArray(expireTime));
            }
        });
        return o != null;
    }
}
