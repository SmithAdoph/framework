package com.adoph.framework.test;

import com.adoph.framework.dao.jpa.BaseDao;
import com.adoph.framework.service.MyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * TODO
 *
 * @author Tangqiandong
 * @version v1.0
 * @since 2018/6/3
 */
@RunWith(SpringRunner.class)
@SpringBootTest
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
