package com.adoph.framework.test;

import com.adoph.framework.permission.pojo.SysUser;
import com.adoph.framework.test.pojo.User;
import com.adoph.framework.util.SysUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Objects;

/**
 * Hibernate:
 * save、update、saveOrUpdate、persist、merge等方法详细测试分析
 * 三种状态：
 * transient(瞬时状态)：刚new出来的对象，还没被保存到数据库，并且没有被持久化
 * persistent(持久化状态)
 * detached(游离状态)
 *
 * @author Adoph
 * @version v1.0
 * @since 2018/3/5
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class HibernateTest {

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * 测试结论：
     * 1.查询到的对象放置在hibernate session容器中
     * 2.每次查询如果hibernate容器中已经存在，直接引用容器中对象
     * 3.查询到的对象状态是persistent持久状态
     */
    @Test
    public void testQuery() {
        Session session = sessionFactory.openSession();
        User u1 = session.get(User.class, 33L);
        User u2 = session.get(User.class, 33L);
        Query query = session.createQuery("from User where userName = 'xxx'");
        List list = query.list();
        System.out.println(u1);
        System.out.println("两次获取的对象比较结果是否相同：" + Objects.equals(u1, u2));
        System.out.println("两次获取的对象比较结果是否相同：" + Objects.equals(u1, list.get(0)));
    }

    /**
     * 1.new出来的对象save过后，由游离状态（detached）变为持久状态（persistent）
     */
    @Test
    public void testSave1() {
        Session session1 = sessionFactory.openSession();
        session1.beginTransaction();//开启事务
        User u3 = new User();
        u3.setUserName("u5");
        u3.setPassword("7762dc5d78cb8366208b0c05b6fb14ea");
        u3.setCreatedBy(1L);
        session1.save(u3);
        u3.setUserName("uef");
        session1.getTransaction().commit();//提交事务
    }

    /**
     * 1.主键的生成策略:
     *
     * @see javax.persistence.GenerationType
     */
    @Test
    public void testSave2() {
        Session session1 = sessionFactory.openSession();
        session1.beginTransaction();//开启事务
        User u3 = new User();
        u3.setId(40L);
        u3.setUserName("like");
        u3.setPassword("7762dc5d78cb8366208b0c05b6fb14ea");
        u3.setCreatedBy(1L);
        session1.save(u3);
        session1.getTransaction().commit();//提交事务
    }

    /**
     * 1.persist类似save方法
     */
    @Test
    public void testPersist() {
        Session session1 = sessionFactory.openSession();
        session1.beginTransaction();//开启事务
        User u3 = new User();
//        u3.setId(33L);
        u3.setUserName("bus");
        u3.setPassword("7762dc5d78cb8366208b0c05b6fb14ea");
        u3.setCreatedBy(1L);
        session1.persist(u3);
        session1.getTransaction().commit();//提交事务
        session1.close();
    }

    /**
     * 1.保存或者更新必须提交事务
     * 2.在事务内，加载对象后（persistent持久状态），如果有修改对象，将会自动生成update语句
     */
    @Test
    public void testUpdate1() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();//开启事务
        User u1 = session.get(User.class, 33L);
        System.out.println("修改前：" + u1);
        u1.setUserName("tdd");//自动生成update语句
        User u2 = session.get(User.class, 33L);
        session.getTransaction().commit();//提交事务
        System.out.println("修改后：" + u2);
    }

    /**
     * 1.同一个session只存在唯一一个相同的持久化对象
     * 2.如果对象id在数据库中存在，则执行更新操作
     */
    @Test
    public void testUpdate2() {
        Session session = sessionFactory.openSession();
        User u1 = session.get(User.class, 33L);
        System.out.println("修改前：" + u1);

        Session session1 = sessionFactory.openSession();
        session1.beginTransaction();//开启事务
        User u3 = new User();
        BeanUtils.copyProperties(u1, u3);
        u3.setUserName("u3");
        session1.saveOrUpdate(u3);
        session1.getTransaction().commit();//提交事务
    }

    /**
     * 1.new出来的对象，update必须设置主键值，并且数据库存在该id
     */
    @Test
    public void testUpdate3() {
        Session session1 = sessionFactory.openSession();
        session1.beginTransaction();//开启事务
        User u3 = new User();
        u3.setId(33L);
        u3.setUserName("u3");
        u3.setPassword("7762dc5d78cb8366208b0c05b6fb14ea");
        u3.setCreatedBy(1L);
        session1.update(u3);
        session1.getTransaction().commit();//提交事务
    }

    /**
     * 1.new出来的对象，merge过后状态没有变化，还是处于游离状态
     * 2.merge的具体实现，是将需要merge的对象copy一个，然后再将copy对象(会被持久化到session中)进行处理
     * 3.merge先执行select，再执行insert或者update
     * 4.如果设置了id，先执行select，select未找到该对象执行insert，反之执行update
     */
    @Test
    public void testMerge() {
        Session session1 = sessionFactory.openSession();
        session1.beginTransaction();//开启事务
        User u3 = new User();
//        u3.setId(33L);
        u3.setId(40L);//数据库未存在的id值
        u3.setUserName("water");
        u3.setPassword("7762dc5d78cb8366208b0c05b6fb14ea");
        u3.setCreatedBy(1L);
        session1.merge(u3);
        u3.setUserName("qs");//此行代码无效，u3依然处于游离状态
        session1.getTransaction().commit();//提交事务
//        User u5 = session1.get(User.class, 33L);
//        System.out.println(u5);
    }
}
