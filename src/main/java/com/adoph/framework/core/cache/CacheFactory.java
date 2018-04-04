package com.adoph.framework.core.cache;

import com.adoph.framework.core.cache.service.RedisCacheService;
import com.adoph.framework.core.cache.service.impl.RedisCache;

/**
 * 缓存工厂
 *
 * @author Adohp
 * @version v1.0
 * @since 2017/9/22
 */
public class CacheFactory {

    private CacheFactory() {
    }

    /**
     * Redis缓存
     */
    private static volatile RedisCacheService<String, Object> redisCache;

    /**
     * Redis 字符串缓存
     */
    private static volatile RedisCacheService<String, String> stringRedisCache;

    /**
     * 获取Redis缓存
     *
     * @return RedisCacheService
     */
    public static RedisCacheService<String, Object> getRedisCache() {
        // 双重检查
        if (redisCache == null) {
            synchronized (CacheFactory.class) {
                if (redisCache == null) {
                    redisCache = new RedisCache<>(false);
                }
            }
        }
        return redisCache;
    }

    /**
     * 获取Redis缓存
     *
     * @return RedisCacheService
     */
    public static RedisCacheService<String, String> getStringRedisCache() {
        // 双重检查
        if (stringRedisCache == null) {
            synchronized (CacheFactory.class) {
                if (stringRedisCache == null) {
                    stringRedisCache = new RedisCache<>(true);
                }
            }
        }
        return stringRedisCache;
    }

}
