package com.adoph.framework.test;

import com.adoph.framework.util.SysUtils;
import org.junit.Test;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;

/**
 * EnumSet测试
 *
 * @author Adoph
 * @version v1.0
 * @since 2018/6/19
 */
public class EnumTest {
    /**
     * range:在某个范围内进行迭代
     */
    @Test
    public void testRange() {
        for (WeekDay day : EnumSet.range(WeekDay.Mon, WeekDay.Fri)) {
            System.out.println(day);
        }
    }

    /**
     * of:子集迭代
     */
    @Test
    public void testOf() {
//        EnumSet<WeekDay> set = EnumSet.of(WeekDay.Mon, WeekDay.Wed, WeekDay.Sat);
//        for (WeekDay day : set) {
//            System.out.println(day);
//        }
        EnumSet<WeekDay> set = EnumSet.noneOf(WeekDay.class);
        set.add(WeekDay.Mon);
        for (WeekDay day : set) {
            System.out.println(day);
        }

    }

    /**
     * EnumMap
     */
    @Test
    public void testMap() {
        EnumMap<WeekDay, Color> map = new EnumMap<>(WeekDay.class);
        WeekDay[] values = WeekDay.values();
        for (int i = 0; i < values.length; i++) {
            map.put(values[i], Color.values()[i]);
        }
        SysUtils.print(map);
    }

    /**
     * 枚举内部方法
     * compareTo 类型比较
     */
    @Test
    public void testInnerMethod() {
        System.out.println(UserType.valueOf("Admin"));//根据枚举常量名称获取枚举
        System.out.println(UserType.Admin.name());//获取枚举常量的名称
        System.out.println(UserType.Admin.ordinal());//返回枚举常量的序数（它在枚举声明中的位置，其中初始常量序数为零）
        //进阶
        System.out.println(MethodType.FIRST.print());//枚举与抽象方法

    }
}

enum WeekDay {
    Mon, Tue, Wed, Thu, Fri, Sat, Sun
}

enum Color {
    Red, Blue, Green, Pink, Black, Gray, White
}

enum UserType {
    Admin(1, "管理员"),
    Vip(2, "超级管理员");

    private int index;
    private String text;

    public int getIndex() {
        return index;
    }

    public String getText() {
        return text;
    }

    UserType(int index, String text) {
        this.index = index;
        this.text = text;
    }
}

enum MethodType {
    FIRST {
        @Override
        public String print() {
            System.out.println("first...");
            return "first";
        }
    };

    abstract String print();
}

enum InterfaceType implements Draw {
    FIRST, SECOND;

    @Override
    public void print() {
        System.out.println("first");
    }
}

interface Draw {
    void print();
}