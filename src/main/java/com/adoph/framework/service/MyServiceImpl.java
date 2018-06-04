package com.adoph.framework.service;

import com.adoph.framework.dao.jpa.BaseDao;
import com.adoph.framework.test.pojo.User;
import com.adoph.framework.test.vo.UserVo;
import com.adoph.framework.util.SysUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * TODO
 *
 * @author Tangqiandong
 * @version v1.0
 * @since 2018/6/4
 */
@Service
public class MyServiceImpl implements MyService {

    @Autowired
    private BaseDao baseDao;

    @Transactional
    @Override
    public void test() {
//        1
//        User user = baseDao.find(User.class, 1L);
//        SysUtils.print(user);

//        2
//        String hql = "from User where id = 8";
//        List<Object> objects = baseDao.find(hql);
//        SysUtils.print(objects);

//        3
//        String hql = "from User where id = ?";
//        List<Object> objects = baseDao.find(hql, 8L);
//        SysUtils.print(objects);

//        4
//        String hql = "from User where id = :id and userName = :userName";
//        Map<String, Object> params = new HashMap<>();
//        params.put("id", 1L);
//        params.put("userName", "mergePersistBean");
//        List<Object> objects = baseDao.find(hql, params);
//        SysUtils.print(objects);

//        5
//        String hql = "from User where id = ? and userName = ?";
//        User user = baseDao.findObject(hql, 8L, "ibm");
//        SysUtils.print(user);

//        6
//        String hql1 = "from User where id = :id and userName = :userName";
//        Map<String, Object> params = new HashMap<>();
//        params.put("id", 1L);
//        params.put("userName", "mergePersistBean");
//        User user1 = baseDao.findObject(hql1, params);
//        SysUtils.print(user1);

//        7
//        String hql = "from User where createdBy = ?";
//        List<User> objects = baseDao.find(hql, 12L);
//        SysUtils.print(objects);
//        baseDao.deleteAll(objects);

//        8
//        String hql = "update User set userName = ? where id = ?";
//        baseDao.update(hql, "updateOp", 1L);

//        9
//        String hql = "update User set userName = :userName where id = :id";
//        Map<String, Object> params = new HashMap<>();
//        params.put("id", 1L);
//        params.put("userName", "mapParams");
//        baseDao.update(hql, params);

//        10
//        List<User> list = baseDao.getListByProperty(User.class, "id", 1L);
//        SysUtils.print(list);

//        11
//        User user = baseDao.getObjectByProperty(User.class, "id", 11L);
//        SysUtils.print(user);

//        12
//        int count = baseDao.count(User.class, "password", "123");
//        SysUtils.print(count);

//        13
//        boolean flag = baseDao.isExisted(User.class, "id", 2L);
//        System.out.println(flag);

//        14
//        String sql1 = "update sys_user set user_name = 'x1' where id = 1";
//        String sql2 = "update sys_user set user_name = 'x2' where id = 11";
//        List<String> sqlList = new ArrayList<>();
//        sqlList.add(sql1);
//        sqlList.add(sql2);
//        baseDao.batchSql(sqlList, 10);

//        15
//        String sql = "select * from sys_user";
//        List<User> list = baseDao.getListBySql(sql, User.class);
//        SysUtils.print(list);

//        16
//        String sql1 = "select * from sys_user where id = ? and user_name = ?";
//        List<User> list1 = baseDao.getListBySql(sql1, User.class, 1L, "x1");
//        SysUtils.print(list1);

//        17
//        String sql2 = "select * from sys_user where id = :id and user_name = :userName";
//        Map<String, Object> params = new HashMap<>();
//        params.put("id", 11L);
//        params.put("userName", "x2");
//        List<User> list2 = baseDao.getListBySql(sql2, User.class, params);
//        SysUtils.print(list2);

//        18
//        String sql = "select * from sys_user";
//        User u1 = baseDao.getObjectBySql(sql, User.class);
//        SysUtils.print(u1);

//        19
//        String sql1 = "select * from sys_user where id = ? and user_name = ?";
//        User u2 = baseDao.getObjectBySql(sql1, User.class, 1L, "x1");
//        SysUtils.print(u2);

//        20
//        String sql2 = "select * from sys_user where id = :id and user_name = :userName";
//        Map<String, Object> params = new HashMap<>();
//        params.put("id", 11L);
//        params.put("userName", "x2");
//        User u3 = baseDao.getObjectBySql(sql2, User.class, params);
//        SysUtils.print(u3);

//        21
//        String sql = "select * from sys_user";
//        List<Map<String, Object>> list1 = baseDao.getListMapBySql(sql);
//        SysUtils.print(list1);

//        22
//        String sql1 = "select * from sys_user where id = ? and user_name = ?";
//        List<Map<String, Object>> list2 = baseDao.getListMapBySql(sql1, 1L, "x1");
//        SysUtils.print(list2);

//        23
//        String sql2 = "select * from sys_user where id = :id and user_name = :userName";
//        Map<String, Object> params = new HashMap<>();
//        params.put("id", 11L);
//        params.put("userName", "x2");
//        List<Map<String, Object>> list3 = baseDao.getListMapBySql(sql2, params);
//        SysUtils.print(list3);

//        24
//        String sql = "select * from sys_user";
//        SysUtils.print(baseDao.getMapBySql(sql));

//        25
//        String sql1 = "select * from sys_user where id = ? and user_name = ?";
//        SysUtils.print(baseDao.getMapBySql(sql1, 1L, "x1"));

//        26
//        String sql2 = "select * from sys_user where id = :id and user_name = :userName";
//        Map<String, Object> params = new HashMap<>();
//        params.put("id", 11L);
//        params.put("userName", "x2");
//        SysUtils.print(baseDao.getMapBySql(sql2, params));

//        27
//        String sql1 = "select count(*) from sys_user";
//        String sql2 = "select user_name from sys_user where id = 1";
//        SysUtils.print(baseDao.getValue(sql1));
//        SysUtils.print(baseDao.getValue(sql2));

//        28
//        String sql3 = "select count(*) from sys_user where id = ?";
//        String sql4 = "select user_name from sys_user where id = ?";
//        SysUtils.print(baseDao.getValue(sql3, 1L));
//        SysUtils.print(baseDao.getValue(sql4, 1L));

//        29
//        String sql5 = "select count(*) from sys_user where id = :id";
//        String sql6 = "select user_name from sys_user where id = :id";
//        Map<String, Object> params = new HashMap<>();
//        params.put("id", 11L);
//        SysUtils.print(baseDao.getValueByMap(sql5, params));
//        SysUtils.print(baseDao.getValueByMap(sql6, params));

//        30
//        String sql1 = "update sys_user set user_name = 'executeUpdate1' where id = 1";
//        String sql2 = "update sys_user set user_name = 'executeUpdate2' where id = ?";
//        String sql3 = "update sys_user set user_name = 'executeUpdate3' where id = :id";
//        SysUtils.print(baseDao.executeUpdate(sql1));
//        SysUtils.print(baseDao.executeUpdate(sql2, 11L));
//        Map<String, Object> params = new HashMap<>();
//        params.put("id", 13L);
//        SysUtils.print(baseDao.executeUpdate(sql3, params));

//        31
//        StringBuilder sql = new StringBuilder(512);
//        sql.append("SELECT  \n");
//        sql.append("	a.id, a.user_name userName, b.role_id roleId, a.create_time createTime \n");
//        sql.append("FROM sys_user a \n");
//        sql.append("JOIN sys_user_role b ON a.id = b.user_id \n");
//        sql.append("WHERE a.id = 1 \n");
//        SysUtils.print(baseDao.queryListBySql(sql.toString(), UserVo.class));

//        32
//        StringBuilder sql1 = new StringBuilder(512);
//        sql1.append("SELECT  \n");
//        sql1.append("	a.id, a.user_name userName, b.role_id roleId, a.create_time createTime \n");
//        sql1.append("FROM sys_user a \n");
//        sql1.append("JOIN sys_user_role b ON a.id = b.user_id \n");
//        sql1.append("WHERE a.id = ? \n");
//        SysUtils.print(baseDao.queryListBySql(sql1.toString(), UserVo.class, 1L));

//        33
//        StringBuilder sql2 = new StringBuilder(512);
//        sql2.append("SELECT  \n");
//        sql2.append("	a.id, a.user_name userName, b.role_id roleId, a.create_time createTime \n");
//        sql2.append("FROM sys_user a \n");
//        sql2.append("JOIN sys_user_role b ON a.id = b.user_id \n");
//        sql2.append("WHERE a.id = :id \n");
//        Map<String, Object> params = new HashMap<>();
//        params.put("id", 11L);
//        SysUtils.print(baseDao.queryListBySql(sql2.toString(), UserVo.class, params));


//        34
//        StringBuilder sql = new StringBuilder(512);
//        sql.append("SELECT  \n");
//        sql.append("	a.id, a.user_name userName, b.role_id roleId, a.create_time createTime \n");
//        sql.append("FROM sys_user a \n");
//        sql.append("JOIN sys_user_role b ON a.id = b.user_id \n");
//        sql.append("WHERE a.id = 1 \n");
//        SysUtils.print(baseDao.queryObjectBySql(sql.toString(), UserVo.class));

//        35
//        StringBuilder sql1 = new StringBuilder(512);
//        sql1.append("SELECT  \n");
//        sql1.append("	a.id, a.user_name userName, b.role_id roleId, a.create_time createTime \n");
//        sql1.append("FROM sys_user a \n");
//        sql1.append("JOIN sys_user_role b ON a.id = b.user_id \n");
//        sql1.append("WHERE a.id = ? \n");
//        SysUtils.print(baseDao.queryObjectBySql(sql1.toString(), UserVo.class, 1L));

//        36
//        StringBuilder sql2 = new StringBuilder(512);
//        sql2.append("SELECT  \n");
//        sql2.append("	a.id, a.user_name userName, b.role_id roleId, a.create_time createTime \n");
//        sql2.append("FROM sys_user a \n");
//        sql2.append("JOIN sys_user_role b ON a.id = b.user_id \n");
//        sql2.append("WHERE a.id = :id \n");
//        Map<String, Object> params = new HashMap<>();
//        params.put("id", 11L);
//        SysUtils.print(baseDao.queryObjectBySql(sql2.toString(), UserVo.class, params));

//        37
//        baseDao.getObjectByProperty(User.class, "password", "123");

//        38
//        baseDao.getObjectBySql("select * from sys_user", User.class);
//        baseDao.getObjectBySql("select * from sys_user where password = ?", User.class, "123");
//        Map<String, Object> params = new HashMap<>();
//        params.put("password", "123");
//        params.put("createdBy", 1L);
//        baseDao.getObjectBySql("select * from sys_user where password = :password and created_by = :createdBy", User.class, params);
    }
}
