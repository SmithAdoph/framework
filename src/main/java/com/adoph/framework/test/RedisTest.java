package com.adoph.framework.test;

import com.adoph.framework.util.RSAEncryptUtils;
import com.adoph.framework.util.SysUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.io.Serializable;
import java.security.KeyPair;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * StringRedisTemplate测试
 *
 * @author Adoph
 * @version v1.0
 * @since 2017/9/20
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Before
    public void setRedisSerializer() {
        redisTemplate.setKeySerializer(new StringRedisSerializer());
    }

    /**
     * Redis序列化方式：
     * 不同的数据类型，采用不同的序列化方式
     */
    @Test
    public void serializerTest() {
//        1.String
//        stringRedisTemplate.opsForValue().set("user1", "admin");
//        redisTemplate.opsForValue().set("user2", "admin");

//        2.TestUser
//        RedisSerializer<String> stringSerializer = new StringRedisSerializer();
//        redisTemplate.setKeySerializer(stringSerializer);
        ValueOperations opsForValue = redisTemplate.opsForValue();
//        TestUser user = new TestUser("TDD", 12);
//        ops.set("user3", user);
//        ops.set("user4", "xxx");
//
//        TestChild child = new TestChild("child");
//        ops.set("user5", child);
//
//        KeyPair keyPair = RSAEncryptUtils.genKeyPair();
//        ops.set("key", keyPair);
//
//        System.out.println(ops.get("user3"));
//        System.out.println(ops.get("user4"));
//        System.out.println(ops.get("user5"));
//        System.out.println(ops.get("key"));

//        ops.increment("user_count", 1);
//        Long user_count = (Long) ops.get("user_count");
//        System.out.println(ops.get("user_count"));

//        2.JSON
        ObjectMapper objectMapper = new ObjectMapper();
        TestChild child1 = new TestChild("test1");
        TestChild child2 = new TestChild("test2");
        try {
            List<TestChild> childList = new ArrayList<>();
            childList.add(child1);
            childList.add(child2);
            ListOperations opsForList = redisTemplate.opsForList();
            opsForList.leftPushAll("test_ops_list", childList);
//            从左往后，自上而下
            TestChild c1 = (TestChild) opsForList.leftPop("test_ops_list");
//            从右往左，自下而上
            TestChild c2 = (TestChild) opsForList.rightPop("test_ops_list");
            SysUtils.print(c1);
            SysUtils.print(c2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void valueOpTest() throws InterruptedException {
        //1.ValueOperations 存储单个值，key相同覆盖值
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        //void set(K key, V value);
//        ops.set("userStr", "admin");
//        ops.set("userStr", "admin1");
//        ops.set("userStr", "admin2");
//        Assert.assertEquals("admin2", ops.get("userStr"));

        //2.void set(K key, V value, long offset);
        //从偏移量offset开始覆盖
        //如果value的长度不足，则用unicode的"\u0000"填充
//        ops.set("userStr1", "1", 5);
//        ops.set("userStr2", "123456", 5);

//        ops.set("str2", "hello");
//        ops.set("str2", "redis", 6);
//        Assert.assertEquals("hello redis", ops.get("str1"));

//        char[] rs = ops.get("userStr1").toCharArray();
//        Assert.assertEquals(UnicodeUtils.encode("1"), UnicodeUtils.decode(ops.get("userStr2")));

        //3.设置过期时间
//        ops.set("userStr3", "timeout", 5, TimeUnit.SECONDS);
//        Assert.assertEquals("timeout", ops.get("userStr3"));
//        Thread.sleep(1000 * 5);
//        Assert.assertNull(ops.get("userStr3"));

//        ops.set("userStr3", "timeout");
//        Assert.assertEquals("timeout", ops.get("userStr3"));
        //4.动态设置某个key的过期时间
//        stringRedisTemplate.expire("userStr3", 3, TimeUnit.SECONDS);
//        Thread.sleep(1000 * 3);
//        Assert.assertNull(ops.get("userStr3"));

//        ops.set("t1", "admin1");
        //5.获取指定key的值，并截取字符串
//        Assert.assertEquals("ad", ops.get("t1", 0, 1));

//        CacheService redisCache = CacheFactory.getRedisCache();
//        redisCache.set("tqd", 123334);
//        Assert.assertEquals(123334, redisCache.get("tqd"));

        //6.multiSet以map的形式添加多条kv
//        Map<String, String> map =  new HashMap<>();
//        map.put("map1", "admin");
//        map.put("map2", "admin1");
//        map.put("map3", "admin2");
//        ops.multiSet(map);

        //7.multiGet以list获取多个key的值
//        List<String> keys = new ArrayList<>();
//        keys.add("map1");
//        keys.add("map2");
//        keys.add("map3");
//        List<String> list = ops.multiGet(keys);
//        for (String str : list) {
//            System.out.println(str);
//        }

        //8.getAndSet设置值并返回旧值
//        Assert.assertEquals("admin", ops.getAndSet("user2", "new"));
//        Assert.assertEquals("new", ops.get("user2"));

        //9.支持long, double
        //increment 增加
//        ops.increment("count", 1);
//        ops.increment("money", 12.23);

        //10.追加字符串，如果为空等同于set
//        ops.append("app1", "com");

        //11.size返回key对应的value的长度
//        Assert.assertEquals(new Long(3), ops.size("app1"));

        //12.setBit设置value的二进制对应的从右至左偏移量的值，true为1，false为0
//        ops.set("bit", "a");//ASCII 97-0110 0010
//        ops.setBit("bit", 2, false);
//        System.out.println(ops.get("bit"));
    }

    @Test
    public void listTest() {
        //opsForList 得到一个集合
        ListOperations ops = stringRedisTemplate.opsForList();

        //leftPush 从集合的头部插入
//        Long t1 = ops.leftPush("userList", "张三");
//        Long t2 = ops.leftPush("userList", "李四");
//        Long t3 = ops.leftPush("userList", "王麻子");
//        System.out.println(t1 + "," + t2 + "," + t3);

//        String[] users = {"x1", "x2", "x3"};
//        String[] users = new String[]{"x1", "x2", "x3"};
//        ops.leftPushAll("userList", users);
        //rightPush 从集合的尾部插入
//        Long t4 = ops.rightPush("userList", "r1");
//        System.out.println(t4);

        //长度
//        Long size = ops.size("userList");

        //leftPushIfPresent 如果存在key，则插入，不存在，不插入；
//        Long t5 = ops.leftPushIfPresent("userList", "aa");
//        System.out.println(t5);

//        Long t6 = ops.leftPushIfPresent("newList", "aa1");
//        System.out.println(t6);
    }

    public void hashTest() {
        HashOperations<String, Object, Object> ops = stringRedisTemplate.opsForHash();
        ops.put("people", "name", "tdd");
        ops.put("people", "age", 12);
        ops.put("people", "sex", "男");

//        ops.putAll();
    }
}

class TestChild implements Serializable {
    private String name;

    public TestChild(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TestChild{" +
                "name='" + name + '\'' +
                '}';
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