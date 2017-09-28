package com.adoph.framework.test;

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

    /**
     * Redis序列化方式：
     * 不同的数据类型，采用不同的序列化方式
     */
    @Test
    public void serializerTest() {
//        1.String
//        stringRedisTemplate.opsForValue().set("user1", "admin");
//        redisTemplate.opsForValue().set("user2", "admin");

//        2.JSON
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.
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
    public void objectTest() {
        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
//        ops.increment()
//        TestUser user = new TestUser("tqd", 12);
//        ops.set("12321ERTY", user);
//        System.out.println(ops.get("12321ERTY"));
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
