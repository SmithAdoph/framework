package com.adoph.framework.test;

import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * BeanUtilsTest
 *
 * @author Adoph
 * @version v1.0
 * @since 2018/1/17
 */
public class BeanUtilsTest {

    /**
     * copyProperties 复制只判断属性名，未判断类型
     */
    @Test
    public void testCopy() {
        MyUser user = new MyUser();
        People people = new People();
        user.setName("testName");
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        user.setArr(list);
        BeanUtils.copyProperties(user, people);
        System.out.println(user);
        System.out.println(people);
//        打印结果如下：
//        User{name='testName', arr=[1, 2]}
//        People{name='testName', arr=[1, 2]}
    }
}

class MyUser {
    private String name;
    private List<Integer> arr;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getArr() {
        return arr;
    }

    public void setArr(List<Integer> arr) {
        this.arr = arr;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", arr=" + arr +
                '}';
    }
}

class People {
    private String name;
    private List<String> arr;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getArr() {
        return arr;
    }

    public void setArr(List<String> arr) {
        this.arr = arr;
    }

    @Override
    public String toString() {
        return "People{" +
                "name='" + name + '\'' +
                ", arr=" + arr +
                '}';
    }
}
