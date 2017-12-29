package com.adoph.framework.test.jpa;

import com.adoph.framework.exception.bean.Error;
import com.adoph.framework.permission.dao.sys.SysUserRepository;
import com.adoph.framework.permission.pojo.SysUser;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.endsWith;

/**
 * Jpa测试
 *
 * @author Adoph
 * @version v1.0
 * @since 2017/11/3
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaTest {

    @Autowired
    SysUserRepository sysUserRepository;

    @Test
    public void testCrud() {
//        SysUser sysUser = new SysUser();
//        sysUser.setUserName("aa");
//        sysUser.setPassword(SecurityUtils.MD5("123"));
//        sysUser.setCreateBy(1L);
//        新增
//        sysUserRepository.save(sysUser);

//        根据id查询
//        SysUser one = sysUserRepository.findOne(12L);
//        System.out.println("更新前：" + one);
//        one.setUserName("update");
//        更新
//        sysUserRepository.save(one);
//        SysUser newOne = sysUserRepository.findOne(12L);
//        System.out.println("更新后：" + newOne);
//        sysUserRepository.

        SysUser queryUser = new SysUser();
        queryUser.setUserName("ADMIN");
        ExampleMatcher m = ExampleMatcher.matching()
                .withIgnoreCase(false)
                .withMatcher("userName", endsWith());
        Example<SysUser> e = Example.of(queryUser, m);
//        SysUser one = sysUserRepository.findOne(e);
        print(sysUserRepository.findAll(e));

    }

    @Test
    public void testParseObjectToJson() {
        Error<String> r = new Error<>();
        r.setUrl("http://112");
        print(r);
    }

    private void print(Object o) {
        try {
            System.out.println(JSONObject.valueToString(o));
        } catch (JSONException e) {
            System.out.println(o.toString());
        }
    }

}
