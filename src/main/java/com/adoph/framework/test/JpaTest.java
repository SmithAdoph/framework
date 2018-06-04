package com.adoph.framework.test;

import com.adoph.framework.test.pojo.User;
import com.adoph.framework.util.SysUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * TODO
 *
 * @author Adoph
 * @version v1.0
 * @since 2018/6/2
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class JpaTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Resource
    private TransactionTemplate transactionTemplate;

    @Test
    public void testEmQuery() {
//        String hql = "from User where id = 1";
//        TypedQuery<User> query = entityManager.createQuery(hql, User.class);
//        List<User> list = query.getResultList();
//        list.get(0).setUserName("123");

//        SysUtils.print(list);
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                User user = entityManager.find(User.class, 1L);
                user.setUserName("123aaa");
                SysUtils.print(user);
            }
        });

//        String sql = "select user_name userName from sys_user WHERE id = 100";
//        SysUtils.print(entityManager.createNativeQuery(sql).getSingleResult());


//        String sql = "SELECT * FROM sys_user WHERE id = 1";
//        StringBuilder sql = new StringBuilder(512);
//        sql.append("select a.id, a.user_name userName, b.role_id roleId from sys_user a \n");
//        sql.append("join sys_user_role b \n");
//        sql.append("on a.id = b.user_id \n");
//        sql.append("where a.id = 1 \n");
//        org.hibernate.Query query = entityManager.createNativeQuery(sql.toString())
//                .unwrap(SQLQuery.class)
//                .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
//        List<Map<String, Object>> list = query.list();
//        List<UserVo> userList = new ArrayList<>();
//        for (Map<String, Object> item : list) {
//            String json = JSON.toJSONString(item);
//            UserVo userVo = JSON.parseObject(json, UserVo.class);
//            userList.add(userVo);
//        }
//        SysUtils.print(list);
//        SysUtils.print(userList);
//        List list = entityManager.createNativeQuery(sql.toString())
//                .unwrap(SQLQuery.class)
//                .addScalar("id", StandardBasicTypes.LONG)
//                .addScalar("userName")
//                .addScalar("roleId", StandardBasicTypes.LONG)
//                .addScalar("sex")
//                .setResultTransformer(Transformers.aliasToBean(UserVo.class))
//                .list();
//        SysUtils.print(list);

    }
}
