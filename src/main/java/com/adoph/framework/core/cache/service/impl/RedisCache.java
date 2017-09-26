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

    public void set(K key, V... value) {
        redisTemplate.opsForSet().add(key, value);
    }

    public void getSet(K key) {
        redisTemplate.opsForSet().members(key);
    }

    public void zSet(K key, V value, double score) {
        redisTemplate.opsForZSet().add(key, value, score);
    }

    public void getZSet(K key) {
        redisTemplate.opsForZSet().range(key, 0, -1);
    }

    public void list() {
//        redisTemplate.opsForList().leftPush()
    }
}

