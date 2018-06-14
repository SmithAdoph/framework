package com.adoph.framework.core.lock;

/**
 * 获取锁后行为抽象
 *
 * @author Adoph
 * @version v1.0
 * @since 2018/6/14
 */
public interface LockAction {

    /**
     * 获取锁后具体执行的业务代码
     *
     * @param locked 加锁是否成功
     */
    void execute(boolean locked);

    /**
     * 锁过期后回滚事务
     */
    void rollback();

}
