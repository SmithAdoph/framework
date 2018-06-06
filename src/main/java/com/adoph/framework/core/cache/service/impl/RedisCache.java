package com.adoph.framework.core.cache.service.impl;

import com.adoph.framework.core.cache.service.RedisCacheService;
import com.adoph.framework.util.SpringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.concurrent.TimeUnit;

/**
 * Redis缓存
 *
 * @author Adoph
 * @version v1.0
 * @date 2017/9/20
 */
public class RedisCache<K, V> implements RedisCacheService<K, V> {

    /**
     * Spring RedisTemplate
     */
    private RedisTemplate<K, V> redisTemplate;

    @SuppressWarnings("all")
    public RedisCache(boolean stringRedisTemplate) {
        if (stringRedisTemplate) {
            redisTemplate = SpringUtils.getBean("stringRedisTemplate", RedisTemplate.class);
        } else {
            redisTemplate = SpringUtils.getBean("redisTemplate", RedisTemplate.class);
            //默认key都是String类型
            redisTemplate.setKeySerializer(new StringRedisSerializer());
        }
    }

    @Override
    public void add(K key, V value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public void add(K key, V value, long timeout, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, timeout, unit);
    }

    @Override
    public V get(Object key) {
        return (V) redisTemplate.opsForValue().get(key);
    }

    @Override
    public void delete(K key) {
        redisTemplate.delete(key);
    }

    @Override
    public void add(K key, Object hashKey, V value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
    }

    @Override
    public Object get(K key, Object hashKey) {
        return redisTemplate.opsForHash().get(key, hashKey);
    }

    @Override
    public void delete(K key, Object... hashKey) {
        redisTemplate.opsForHash().delete(key, hashKey);
    }

    @Override
    public Long increment(K key, long delta) {
        return redisTemplate.opsForValue().increment(key, delta);
    }

    @Override
    public Double increment(K key, double delta) {
        return redisTemplate.opsForValue().increment(key, delta);
    }

    @Override
    public void expire(K key, long timeout, TimeUnit unit) {
        redisTemplate.expire(key, timeout, unit);
    }

    /**
     * 获取递增的值
     *
     * @param key 键
     * @return Long
     */
    public Long get(final String key) {
        return redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                byte[] b1 = serializer.serialize(key);
                byte[] b2 = connection.get(b1);
                try {
                    String val = serializer.deserialize(b2);
                    return Long.parseLong(val);
                } catch (Exception e) {
                    return 0L;
                }
            }
        });
    }
}

