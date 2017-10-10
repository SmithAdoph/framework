package com.adoph.framework.core.cache.service.impl;

import com.adoph.framework.core.cache.service.CacheService;
import com.adoph.framework.util.SpringUtils;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * Redis缓存
 *
 * @author Adoph
 * @version v1.0
 * @since 2017/9/20
 */
public class RedisCache<K, V> implements CacheService<K, V> {

    /**
     * Spring RedisTemplate
     */
    private RedisTemplate<K, V> redisTemplate = SpringUtils.getBean("redisTemplate", RedisTemplate.class);

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
}

