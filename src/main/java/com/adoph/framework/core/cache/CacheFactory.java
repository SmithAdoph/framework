package com.adoph.framework.core.cache;

import com.adoph.framework.core.cache.service.CacheService;
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
    private static volatile CacheService redisCache;

    /**
     * 获取Redis缓存
     *
     * @return CacheService
     */
    public static CacheService getRedisCache() {
        // 双重检查
        if (redisCache == null) {
            synchronized (CacheFactory.class) {
                if (redisCache == null) {
                    redisCache = new RedisCache();
                }
            }
        }
        return redisCache;
    }

}
