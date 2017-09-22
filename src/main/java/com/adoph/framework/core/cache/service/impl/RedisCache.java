package com.adoph.framework.core.cache.service.impl;

import com.adoph.framework.core.cache.service.CacheService;
import com.adoph.framework.util.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * Redis缓存
 *
 * @author Adoph
 * @version v1.0
 * @since 2017/9/20
 */
public class RedisCache<K, V> implements CacheService {

    private RedisTemplate redisTemplate = SpringUtils.getBean("redisTemplate");

    @Override
    public void set(Object key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public void set(Object key, Object value, long timeout, TimeUnit unit) {

    }

    @Override
    public Object get(Object key) {
        return null;
    }
}
