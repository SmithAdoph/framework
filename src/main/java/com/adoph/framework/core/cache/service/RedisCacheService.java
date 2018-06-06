package com.adoph.framework.core.cache.service;

import java.util.concurrent.TimeUnit;

/**
 * Redis缓存抽象
 *
 * @author Adoph
 * @version v1.0
 * @date 2017/9/21
 */
public interface RedisCacheService<K, V> extends CacheService {

    /**
     * 缓存
     *
     * @param key   非空
     * @param value 值
     */
    void add(K key, V value);

    /**
     * 缓存，并设置过期时间
     *
     * @param key     非空
     * @param value   值
     * @param timeout 过期时间
     * @param unit    时间单位
     */
    void add(K key, V value, long timeout, TimeUnit unit);

    /**
     * 获取缓存值
     *
     * @param key 非空
     * @return V
     */
    V get(Object key);

    /**
     * 删除
     *
     * @param key 键值
     */
    void delete(K key);

    /**
     * 缓存
     *
     * @param key     键
     * @param hashKey hash键
     * @param value   值
     */
    void add(K key, Object hashKey, V value);

    /**
     * 获取缓存
     *
     * @param key     键
     * @param hashKey hash键
     * @return V
     */
    Object get(K key, Object hashKey);

    /**
     * 删除缓存
     *
     * @param key     键
     * @param hashKey hash键
     */
    void delete(K key, Object... hashKey);

    /**
     * 缓存递增
     *
     * @param key   键
     * @param delta 递增值
     */
    Long increment(K key, long delta);

    /**
     * 缓存递增
     *
     * @param key   键
     * @param delta 递增值
     */
    Double increment(K key, double delta);

    /**
     * 设置缓存过期时间
     *
     * @param key     键
     * @param timeout 过期时间
     * @param unit    时间单位
     */
    void expire(K key, long timeout, TimeUnit unit);

}
