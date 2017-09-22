package com.adoph.framework.test;

import com.adoph.framework.Application;
import com.adoph.framework.core.cache.CacheFactory;
import com.adoph.framework.core.cache.service.CacheService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.Serializable;

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

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * RedisCache
     */
    private CacheService redisCache = CacheFactory.getRedisCache();

    @Test
    public void valueOpTest() throws InterruptedException {
        //ValueOperations 存储单个值，key相同覆盖值
//        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        //void set(K key, V value);
//        ops.set("userStr", "admin");
//        ops.set("userStr", "admin1");
//        ops.set("userStr", "admin2");
//        Assert.assertEquals("admin2", ops.get("userStr"));

        //void set(K key, V value, long offset);
        //offset指定value的长度，长度不够用unicode的"\u0000"填充
//        ops.set("userStr1", "1", 5);
//        ops.set("userStr2", "123456", 5);

//        char[] rs = ops.get("userStr1").toCharArray();
//        Assert.assertEquals(UnicodeUtils.encode("1"), UnicodeUtils.decode(ops.get("userStr2")));

        //设置过期时间
//        ops.set("userStr3", "timeout", 5, TimeUnit.SECONDS);
//        Assert.assertEquals("timeout", ops.get("userStr3"));
//        Thread.sleep(1000 * 5);
//        Assert.assertNull(ops.get("userStr3"));

//        ops.set("userStr3", "timeout");
//        Assert.assertEquals("timeout", ops.get("userStr3"));
//        //动态设置某个key的过期时间
//        stringRedisTemplate.expire("userStr3", 3, TimeUnit.SECONDS);
//        Thread.sleep(1000 * 3);
//        Assert.assertNull(ops.get("userStr3"));

//        ops.set("t1", "admin1");
        //获取指定key的值，并截取字符串
//        Assert.assertEquals("ad", ops.get("t1", 0, 1));

        redisCache.set("tqd", 123334);
        Assert.assertEquals(123334, redisCache.get("tqd"));
    }

    @Test
    public void objectTest() {
        ValueOperations<String, TestUser> ops = redisTemplate.opsForValue();
        TestUser user = new TestUser("tqd", 12);
        ops.set("12321ERTY", user);
        System.out.println(ops.get("12321ERTY"));
    }

    @Test
    public void stringTest() {
//        stringRedisTemplate.opsForCluster();
//        stringRedisTemplate.opsForGeo();

//        HashOperations<String, Object, Object> opsForHash = stringRedisTemplate.opsForHash();
//        opsForHash.put("pwd", "QWERTYUI", "123");
//        Assert.assertEquals("123", opsForHash.get("pwd", "QWERTYUI"));
//        stringRedisTemplate.opsForHyperLogLog();

        //1.ListOperations按照push顺序存储(类似栈，先进后出)
//        ListOperations<String, String> opsForList = stringRedisTemplate.opsForList();
//        opsForList.leftPush("user", "admin");
//        opsForList.leftPush("user", "admin1");
//        opsForList.leftPush("user", "admin2");

        //挨个移除，并返回移除的值(从上往下)
//        Assert.assertEquals("admin2", opsForList.leftPop("user"));
//        RedisOperations<String, String> operations = opsForList.getOperations();
//        System.out.println(operations);

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

class TestUser implements Serializable {
    TestUser(String name, int age) {
        this.name = name;
        this.age = age;
    }

    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "TestUser{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
