package com.adoph.framework.core.cache;

/**
 * 缓存抽象
 *
 * @author Adoph
 * @version v1.0
 * @since 2017/9/21
 */
public abstract class AbstractCache<K, V> {

    /**
     * 添加缓存对象
     *
     * @param k key
     * @param v value
     */
    public abstract void add(K k, V v);

}
