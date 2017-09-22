package com.adoph.framework.core.cache.service;

import java.util.concurrent.TimeUnit;

/**
 * 缓存抽象
 *
 * @author Adoph
 * @version v1.0
 * @since 2017/9/21
 */
public interface CacheService<K, V> {

    /**
     * 缓存
     *
     * @param key   非空
     * @param value
     */
    void set(K key, V value);

    /**
     * 缓存，并设置过期时间
     *
     * @param key     非空
     * @param value
     * @param timeout 过期时间
     * @param unit    时间单位
     */
    void set(K key, V value, long timeout, TimeUnit unit);

    /**
     * 获取缓存值
     *
     * @param key 非空
     * @return V
     */
    V get(Object key);

}
