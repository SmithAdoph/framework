package com.adoph.framework.dao.jpa;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 基础DAO接口
 *
 * @author Adoph
 * @version v1.0
 * @date 2018/6/1
 */
public interface BaseDao {

    /**
     * 开放EntityManager，当你想是用EntityManager的其他接口
     * 说明：EntityManager是JPA对hibernate的二次封装接口
     *
     * @see javax.persistence.EntityManager
     */
    EntityManager getEntityManager();

    /**
     * 通过对象标识获得对象
     *
     * @param clazz 对象类型
     * @param id    主键标识
     * @return 对象
     */
    <T> T find(Class<T> clazz, Serializable id);

    /**
     * 查找对象结果集
     *
     * @param hql HQL > from SysUser where id=1
     * @return 对象结果集
     */
    <T> List<T> find(String hql);

    /**
     * 查找对象结果集
     *
     * @param hql HQL > from SysUser where id=? and name=?
     * @return 对象结果集
     */
    <T> List<T> find(String hql, Object... params);

    /**
     * 查找对象结果集
     *
     * @param hql HQL > from SysUser where id=:id and name=:name
     * @param map 键值对参数
     * @return 对象结果集
     */
    <T> List<T> find(String hql, Map<String, Object> map);

    /**
     * 查找对象
     *
     * @param hql    HQL
     * @param params 多个参数
     * @return 对象结果集
     */
    <T> T findObject(String hql, Object... params);

    /**
     * 查找对象
     *
     * @param hql HQL
     * @param map 键值对参数
     * @return 对象结果集
     */
    <T> T findObject(String hql, final Map<String, Object> map);

    /**
     * 更新或者保存对象(晦涩)
     * 说明：
     * 1.entity对象将会被hibernate进行持久化管理
     * 2.设置了id，在hibernate上下文中未找到该实体，抛出异常
     * 在hibernate上下文中找到该实体，则进行更新操作
     * 3.未设置id，将在数据库新增一条数据
     *
     * @param entity 待更新或者保存对象
     */
    void persist(Object entity);

    /**
     * 更新或者保存对象(晦涩)
     * 说明：
     * 1.entity对象不会被hibernate进行持久化管理
     * 2.设置id，在hibernate上下文找到该对象进行更新操作，未找到新增（若设置自增id，以数据库返回id值保存）
     * 3.未设置id，新增
     *
     * @param entity 待更新或者保存对象
     */
    <T> T merge(T entity);

    /**
     * 删除对象
     *
     * @param entity 待删除对象
     */
    void delete(Object entity);

    /**
     * 删除对象集合
     *
     * @param entityList 对象集合
     */
    void deleteAll(Collection<?> entityList);

    /**
     * 更新操作
     *
     * @param hql    HQL
     * @param params 参数
     */
    int update(String hql, Object... params);

    /**
     * 更新操作
     *
     * @param hql HQL
     */
    int update(String hql);

    /**
     * 更新操作
     *
     * @param hql HQL
     * @param map 参数
     */
    int update(String hql, Map<String, Object> map);

    /**
     * 根据对象属性获得对象
     *
     * @param clazz         对象类型
     * @param propertyName  属性名
     * @param propertyValue 属性值
     * @return 对象集合
     */
    <T> List<T> getListByProperty(Class<T> clazz, String propertyName, Object propertyValue);

    /**
     * 根据对象属性获得对象
     *
     * @param clazz         对象类型
     * @param propertyName  属性名
     * @param propertyValue 属性值
     * @return 对象
     */
    <T> T getObjectByProperty(Class<T> clazz, String propertyName, Object propertyValue);

    /**
     * 通过对象属性查询对象数量
     *
     * @param clazz         对象类型
     * @param propertyName  属性名
     * @param propertyValue 属性值
     * @return 对象数量
     */
    int count(Class<?> clazz, String propertyName, Object propertyValue);

    /**
     * 通过对象属性查询对象是否存在
     *
     * @param clazz         对象类型
     * @param propertyName  属性名
     * @param propertyValue 属性值
     * @return boolean
     */
    boolean isExisted(Class<?> clazz, String propertyName, Object propertyValue);

    /**
     * 批量保存
     *
     * @param entityList 对象集合
     * @param batchSize  批量更新数
     */
    void mergeAll(List<?> entityList, int batchSize);

    /**
     * 批量更新
     *
     * @param entityList 待更新对象集合
     * @param batchSize  批量更新数
     */
    void persistAll(List<?> entityList, int batchSize);

    /**
     * 批量执行SQL
     *
     * @param sqlList   待更新sql集合
     * @param batchSize 批量执行数
     */
    void batchSql(List<String> sqlList, int batchSize);


    /**
     * 取得对象集
     *
     * @param sql   查询SQL
     * @param clazz 对象类型
     * @return 对象集合
     */
    <T> List<T> getListBySql(String sql, Class<T> clazz);

    /**
     * 取得对象集
     *
     * @param sql    查询SQL
     * @param clazz  对象类型
     * @param params 查询参数
     * @return 对象集合
     */
    <T> List<T> getListBySql(String sql, Class<T> clazz, Object... params);

    /**
     * 取得对象集
     *
     * @param sql   select * from sys_user where id = :id and user_name = :userName
     * @param clazz 对象类型
     * @param map   查询参数
     * @return 对象集合
     */
    <T> List<T> getListBySql(String sql, Class<T> clazz, Map<String, Object> map);

    /**
     * 取得对象
     *
     * @param sql   查询SQL
     * @param clazz 对象类型
     * @return 对象
     */
    <T> T getObjectBySql(String sql, Class<T> clazz);

    /**
     * 取得对象
     *
     * @param sql    查询SQL
     * @param clazz  对象类型
     * @param params 查询参数
     * @return 对象
     */
    <T> T getObjectBySql(String sql, Class<T> clazz, Object... params);

    /**
     * 取得对象
     *
     * @param sql   查询SQL
     * @param clazz 对象类型
     * @param map   查询参数
     * @return 对象
     */
    <T> T getObjectBySql(String sql, Class<T> clazz, Map<String, Object> map);

    /**
     * 返回List<Map<String,Object>的数据
     *
     * @param sql 查询SQL
     * @return 对象
     */
    List<Map<String, Object>> getListMapBySql(String sql);

    /**
     * 返回List<Map<String,Object>的数据
     *
     * @param sql    查询SQL
     * @param params 查询参数
     * @return 对象
     */
    List<Map<String, Object>> getListMapBySql(String sql, Object... params);


    /**
     * 返回List<Map<String,Object>的数据
     *
     * @param sql 查询SQL
     * @param map 查询参数
     * @return 对象
     */
    List<Map<String, Object>> getListMapBySql(String sql, Map<String, Object> map);

    /**
     * 返回 Map<String,Object> 的数据
     *
     * @param sql 查询SQL
     * @param map 查询参数
     * @return 对象
     */
    Map<String, Object> getMapBySql(String sql, Map<String, Object> map);

    /**
     * 返回 Map<String,Object> 的数据
     *
     * @param sql    查询SQL
     * @param params 查询参数
     * @return 对象
     */
    Map<String, Object> getMapBySql(String sql, Object... params);

    /**
     * 取得值
     * select count(1) from table_name <br>
     * select user_name from user_info where user_id=1
     *
     * @param sql 查询SQL
     * @return 整数
     */
    Object getValue(String sql);

    /**
     * 取得值
     * select count(1) from table_name
     * select user_name from user_info where user_id=1
     *
     * @param sql    查询SQL
     * @param params 参数
     * @return 整数
     */
    <T> T getValue(String sql, Object... params);

    /**
     * 取得值
     * select user_name from user_info where user_id= :userId
     *
     * @param sql    查询SQL
     * @param params 参数
     * @return 整数
     */
    <T> T getValueByMap(String sql, Map<String, Object> params);

    /**
     * 执行更新
     *
     * @param sql 执行脚本
     * @return 更新记录数
     */
    int executeUpdate(String sql);

    /**
     * 批量执行SQL
     *
     * @param sql 执行脚本
     */
    int executeUpdate(final String sql, final Object... params);

    /**
     * 执行更新
     * update set user_info name= :name where user_id= :user_id
     *
     * @param sql    执行脚本
     * @param params 参数
     * @return int
     */
    int executeUpdate(final String sql, Map<String, Object> params);

    /**
     * 批量执行SQL
     *
     * @param sqlList 脚本集合
     */
    void executeUpdate(List<String> sqlList);

    /**
     * 批量执行
     *
     * @param sql        insert into user_info(code,name,sex) params(?, ?, ?)
     * @param paramsList 请求参数
     * @return Integer
     */
    Integer executeBatch(String sql, List<List<Object>> paramsList);

    /**
     * 查询返回自定义beanList
     *
     * @param sql    select a.col1, b.col2 from table1 a join table2 b on a.id = b.aid where a.id = ?
     * @param clazz  对象类型
     * @param params 查询参数
     * @return List
     */
    <T> List<T> queryListBySql(String sql, Class<T> clazz, Object... params);

    /**
     * 查询返回自定义beanList
     *
     * @param sql   select a.col1, b.col2 from table1 a join table2 b on a.id = b.aid where a.id = ?
     * @param clazz 对象类型
     * @return List
     */
    <T> List<T> queryListBySql(String sql, Class<T> clazz);

    /**
     * 查询返回自定义beanList
     *
     * @param sql   select a.col1, b.col2 from table1 a join table2 b on a.id = b.aid where a.id = ?
     * @param clazz 对象类型
     * @param map   查询参数
     * @return List
     */
    <T> List<T> queryListBySql(String sql, Class<T> clazz, Map<String, Object> map);

    /**
     * 查询返回自定义bean
     *
     * @param sql    select a.col1, b.col2 from table1 a join table2 b on a.id = b.aid where a.id = ?
     * @param clazz  对象类型
     * @param params 查询参数
     * @return Object
     */
    <T> T queryObjectBySql(String sql, Class<T> clazz, Object... params);

    /**
     * 查询返回自定义bean
     *
     * @param sql   select a.col1, b.col2 from table1 a join table2 b on a.id = b.aid where a.id = ?
     * @param clazz 对象类型
     * @param map   查询参数
     * @return Object
     */
    <T> T queryObjectBySql(String sql, Class<T> clazz, Map<String, Object> map);
}
