package com.adoph.framework.test;

import com.adoph.framework.Application;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * StringRedisTemplate测试
 *
 * @author Adoph
 * @version v1.0
 * @since 2017/9/20
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class RedisTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @org.junit.Test
    public void stringTest() {
//        ValueOperations<String, String> vo = stringRedisTemplate.opsForValue();
//        vo.set("user", "小王");
//        Assert.assertEquals("小王", vo.get("user"));
//        stringRedisTemplate.expire()

//        vo.set("pwd", "123", 1000);//长度不够用unicode(\u0000, null)填充
//        vo.set("pwd", "123", 5, TimeUnit.SECONDS);//缓存值，并设置有效时间
//        vo.setBit()
//        System.out.println(vo.get("pwd"));
//        try {
//            Thread.sleep(1000 * 5);
//            System.out.println(vo.get("pwd"));
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

//        stringRedisTemplate.opsForCluster();
//        stringRedisTemplate.opsForGeo();

//        HashOperations<String, Object, Object> opsForHash = stringRedisTemplate.opsForHash();
//        opsForHash.put("pwd", "QWERTYUI", "123");
//        Assert.assertEquals("123", opsForHash.get("pwd", "QWERTYUI"));
//        stringRedisTemplate.opsForHyperLogLog();
        ListOperations<String, String> opsForList = stringRedisTemplate.opsForList();
//        opsForList.
//        stringRedisTemplate.opsForValue();
//        SetOperations<String, String> opsForSet = stringRedisTemplate.opsForSet();
////        opsForSet.add("user", "t2", "t1", "t6", "t5");
//        opsForSet.remove("user", "5");
//        Set<String> user = opsForSet.members("user");
//        System.out.println(user);

//        ZSetOperations<String, String> forZSet = stringRedisTemplate.opsForZSet();
//        forZSet.add("company", "sm1", 2);
//        forZSet.add("company", "sm2", 1);
//        forZSet.add("company", "sm3", 5);
//        Set<String> company = forZSet.range("company", 0, 2);
//        System.out.println(company);
    }

}
