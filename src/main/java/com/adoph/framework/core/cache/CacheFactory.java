package com.adoph.framework.core.cache;

import com.adoph.framework.core.cache.service.RedisCacheService;
import com.adoph.framework.core.cache.service.impl.RedisCache;

/**
 * 缓存工厂
 *
 * @author Adohp
 * @version v1.0
 * @date 2017/9/22
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
     * <p>
     * volatile 避免实例未及时刷新到主内存中
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
     * <p>
     * 双重检查的作用：
     * 线程t1、t2获取实例，当前实例为空，当t1、t2同时通过第一次检查，t2拿到锁创建实例。
     * t1等待t2执行完成，此时实例已经创建，t1拿到锁继续创建实例，导致重复创建实例的问题（降低程序效率）。
     * 为了避免这种问题，在获取到锁后再进行第二次检查，如果实例已经存在则直接返回。
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
