package com.adoph.framework.core.cache.redis;

import com.adoph.framework.core.cache.AbstractCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * Redis缓存
 *
 * @author Adoph
 * @version v1.0
 * @since 2017/9/20
 */
public class RedisCache extends AbstractCache {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void add(Object o, Object o2) {
//        stringRedisTemplate.opsf
    }
}
