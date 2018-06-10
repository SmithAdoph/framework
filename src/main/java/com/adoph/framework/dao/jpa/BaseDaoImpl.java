package com.adoph.framework.dao.jpa;

import com.adoph.framework.util.SqlUtils;
import com.alibaba.fastjson.JSON;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.*;

/**
 * 基础DAO实现
 * 说明：基于javax.persistence.EntityManager再次封装
 *
 * @author Adoph
 * @version v1.0
 * @see javax.persistence.EntityManager
 * @date 2018/6/1
 */
@Repository
public class BaseDaoImpl implements BaseDao {

    private Logger logger = LoggerFactory.getLogger(BaseDaoImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public <T> T find(Class<T> clazz, Serializable id) {
        return entityManager.find(clazz, id);
    }

    @Override
    public <T> List<T> find(final String hql) {
        return find(hql, new Object[]{});
    }

    @SuppressWarnings("all")
    @Override
    public <T> List<T> find(final String hql, final Object... params) {
        Query query = entityManager.createQuery(SqlUtils.toJpaSql(hql));
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                query.setParameter(i, params[i]);
            }
        }
        return (List<T>) query.getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> List<T> find(final String hql, final Map<String, Object> map) {
        Query query = entityManager.createQuery(hql);
        Set<Map.Entry<String, Object>> entries = map.entrySet();
        for (Map.Entry<String, Object> item : entries) {
            query.setParameter(item.getKey(), item.getValue());
        }
        return (List<T>) query.getResultList();
    }

    @Override
    public <T> T findObject(final String hql, final Object... params) {
        List<T> list = find(hql, params);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public <T> T findObject(final String hql, final Map<String, Object> map) {
        List<T> list = find(hql, map);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public void persist(Object entity) {
        entityManager.persist(entity);
    }

    @Override
    public <T> T merge(T entity) {
        return entityManager.merge(entity);
    }

    @Override
    public void delete(Object entity) {
        entityManager.remove(entity);
    }

    @Override
    public void deleteAll(Collection<?> entityList) {
        for (Object entity : entityList) {
            delete(entity);
        }
    }

    @Override
    public int update(final String hql, final Object... params) {
        Query query = entityManager.createQuery(SqlUtils.toJpaSql(hql));
        for (int i = 0; i < params.length; i++) {
            query.setParameter(i, params[i]);
        }
        return query.executeUpdate();
    }

    @Override
    public int update(String hql) {
        return update(hql, new Object[]{});
    }

    @Override
    public int update(final String hql, final Map<String, Object> map) {
        Query query = entityManager.createQuery(hql);
        Set<Map.Entry<String, Object>> entries = map.entrySet();
        for (Map.Entry<String, Object> entry : entries) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        return query.executeUpdate();
    }

    @Override
    public <T> List<T> getListByProperty(Class<T> clazz, final String propertyName, final Object propertyValue) {
        String hql = "from " + clazz.getSimpleName() + " where " + propertyName + " = ?";
        return find(hql, propertyValue);
    }

    @SuppressWarnings("all")
    @Override
    public <T> T getObjectByProperty(Class<T> clazz, final String propertyName, final Object propertyValue) {
        List<T> list = getListByProperty(clazz, propertyName, propertyValue);
        if (list.size() > 1) {
            throw new RuntimeException(String.format("存在多条数据，总共查询到{%d}条！查询参数：propertyName=%s，propertyValue=%s",
                    list.size(), propertyName, propertyValue));
        }
        return list.isEmpty() ? null : (T) list.get(0);
    }

    @Override
    public int count(Class<?> clazz, final String propertyName, final Object propertyValue) {
        return getListByProperty(clazz, propertyName, propertyValue).size();
    }

    @Override
    public boolean isExisted(Class<?> clazz, final String propertyName, final Object propertyValue) {
        return getListByProperty(clazz, propertyName, propertyValue).size() > 0;
    }

    @Override
    public void mergeAll(List<?> entityList, int batchSize) {
        for (int i = 0; i < entityList.size(); i++) {
            merge(entityList.get(i));
            if (i % batchSize == 0) {
                entityManager.flush();//立即更新到数据库
                entityManager.clear();//清空持久化上下文
            }
        }
    }

    @Override
    public void persistAll(List<?> entityList, int batchSize) {
        for (int i = 0; i < entityList.size(); i++) {
            persist(entityList.get(i));
            if (i % batchSize == 0) {
                entityManager.flush();//立即更新到数据库
                entityManager.clear();//清空持久化上下文
            }
        }
    }

    @Override
    public void batchSql(List<String> sqlList, int batchSize) {
        for (int i = 0; i < sqlList.size(); i++) {
            executeUpdate(sqlList.get(i));
            if (i % batchSize == 0) {
                entityManager.flush();//立即更新到数据库
            }
        }
    }

    @Override
    public <T> List<T> getListBySql(final String sql, Class<T> clazz) {
        return getListBySql(sql, clazz, new Object[]{});
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> List<T> getListBySql(final String sql, Class<T> clazz, final Object... params) {
        Query query = entityManager.createNativeQuery(SqlUtils.toJpaSql(sql), clazz);
        for (int i = 0; i < params.length; i++) {
            query.setParameter(i, params[i]);
        }
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> List<T> getListBySql(final String sql, Class<T> clazz, final Map<String, Object> map) {
        Query query = entityManager.createNativeQuery(sql, clazz);
        Set<Map.Entry<String, Object>> entries = map.entrySet();
        for (Map.Entry<String, Object> entry : entries) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        return query.getResultList();
    }

    @Override
    public <T> T getObjectBySql(final String sql, Class<T> clazz) {
        List<T> list = getListBySql(sql, clazz);
        if (list.size() > 1) {
            throw new RuntimeException(String.format("存在多条数据，总共查询到{%d}条！sql=[%s]",
                    list.size(), sql));
        }
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public <T> T getObjectBySql(String sql, Class<T> clazz, final Object... params) {
        List<T> list = getListBySql(sql, clazz, params);
        if (list.size() > 1) {
            throw new RuntimeException(String.format("存在多条数据，总共查询到{%d}条！查询参数%s，执行sql=[%s]",
                    list.size(), Arrays.toString(params), sql));
        }
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public <T> T getObjectBySql(String sql, Class<T> clazz, final Map<String, Object> map) {
        List<T> list = getListBySql(sql, clazz, map);
        if (list.size() > 1) {
            throw new RuntimeException(String.format("存在多条数据，总共查询到{%d}条！查询参数%s，执行sql=[%s]",
                    list.size(), map.toString(), sql));
        }
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<Map<String, Object>> getListMapBySql(final String sql) {
        return getListMapBySql(sql, new Object[]{});
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Map<String, Object>> getListMapBySql(final String sql, final Object... params) {
        org.hibernate.Query query = entityManager.createNativeQuery(sql)
                .unwrap(SQLQuery.class)
                .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        for (int i = 0; i < params.length; i++) {
            query.setParameter(i, params[i]);
        }
        return query.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Map<String, Object>> getListMapBySql(final String sql, Map<String, Object> map) {
        org.hibernate.Query query = entityManager.createNativeQuery(sql)
                .unwrap(SQLQuery.class)
                .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        Set<Map.Entry<String, Object>> entries = map.entrySet();
        for (Map.Entry<String, Object> entry : entries) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        return query.list();
    }

    @Override
    public Map<String, Object> getMapBySql(final String sql, final Map<String, Object> map) {
        List<Map<String, Object>> list = getListMapBySql(sql, map);
        if (list.size() > 1) {
            throw new RuntimeException(String.format("存在多条数据，总共查询到{%d}条！查询参数%s，执行sql=[%s]",
                    list.size(), map.toString(), sql));
        }
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public Map<String, Object> getMapBySql(final String sql, final Object... params) {
        List<Map<String, Object>> list = getListMapBySql(sql, params);
        if (list.size() > 1) {
            throw new RuntimeException(String.format("存在多条数据，总共查询到{%d}条！查询参数%s，执行sql=[%s]",
                    list.size(), Arrays.toString(params), sql));
        }
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public Object getValue(final String sql) {
        return entityManager.createNativeQuery(sql).getResultList().get(0);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getValue(final String sql, final Object... params) {
        Query query = entityManager.createNativeQuery(SqlUtils.toJpaSql(sql));
        for (int i = 0; i < params.length; i++) {
            query.setParameter(i, params[i]);
        }
        List list = query.getResultList();
        return list.isEmpty() ? null : (T) list.get(0);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getValueByMap(final String sql, final Map<String, Object> params) {
        Query query = entityManager.createNativeQuery(sql);
        Set<Map.Entry<String, Object>> entries = params.entrySet();
        for (Map.Entry<String, Object> entry : entries) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        List list = query.getResultList();
        return list.isEmpty() ? null : (T) list.get(0);
    }

    @Override
    public int executeUpdate(final String sql) {
        return executeUpdate(sql, new Object[]{});
    }

    @Override
    public int executeUpdate(final String sql, final Object... params) {
        Query query = entityManager.createNativeQuery(SqlUtils.toJpaSql(sql));
        for (int i = 0; i < params.length; i++) {
            query.setParameter(i, params[i]);
        }
        return query.executeUpdate();
    }

    @Override
    public int executeUpdate(final String sql, final Map<String, Object> params) {
        Query query = entityManager.createNativeQuery(sql);
        Set<Map.Entry<String, Object>> entries = params.entrySet();
        for (Map.Entry<String, Object> entry : entries) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        return query.executeUpdate();
    }

    @Override
    public void executeUpdate(final List<String> sqlList) {
        for (String sql : sqlList) {
            executeUpdate(sql);
        }
    }

    @Override
    public Integer executeBatch(final String sql, final List<List<Object>> paramsList) {
        for (List<Object> params : paramsList) {
            executeUpdate(sql, params.toArray());
        }
        return paramsList.size();
    }

    @Override
    public <T> List<T> queryListBySql(String sql, Class<T> clazz, Object... params) {
        return JSON.parseArray(JSON.toJSONString(getListMapBySql(sql, params)), clazz);
    }

    @Override
    public <T> List<T> queryListBySql(String sql, Class<T> clazz) {
        return JSON.parseArray(JSON.toJSONString(getListMapBySql(sql, new Object[]{})), clazz);
    }

    @Override
    public <T> List<T> queryListBySql(String sql, Class<T> clazz, Map<String, Object> map) {
        return JSON.parseArray(JSON.toJSONString(getListMapBySql(sql, map)), clazz);
    }

    @Override
    public <T> T queryObjectBySql(String sql, Class<T> clazz, Object... params) {
        List<T> list = queryListBySql(sql, clazz, params);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public <T> T queryObjectBySql(String sql, Class<T> clazz, Map<String, Object> map) {
        List<T> list = queryListBySql(sql, clazz, map);
        return list.isEmpty() ? null : list.get(0);
    }

}
