package com.adoph.framework.test.spring;

import com.adoph.framework.Application;
import com.adoph.framework.dao.jpa.BaseDao;
import com.adoph.framework.service.MyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * 基础BaseDao测试
 *
 * @author Adoph
 * @version v1.0
 * @date 2018/6/3
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class BaseDaoTest {

    @Resource
    private BaseDao baseDao;

    @Resource
    private MyService myService;

    @Test
    public void test() {
        myService.test();
    }
}
