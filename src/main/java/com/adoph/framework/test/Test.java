//package com.adoph.framework.test;
//
//import org.junit.Assert;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.data.redis.core.ValueOperations;
//
///**
// * 测试
// *
// * @author Adoph
// * @version v1.0
// * @since 2017/9/20
// */
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class Test {
//
//    @Autowired
//    private StringRedisTemplate stringRedisTemplate;
//
//    @org.junit.Test
//    public void redisTest() {
//        ValueOperations<String, String> vo = stringRedisTemplate.opsForValue();
//        vo.set("user", "小王");
//        Assert.assertEquals(vo.get("user"), "小王");
//    }
//
//}
//测试
