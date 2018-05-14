package com.adoph.framework.test.common;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * 简单测试
 *
 * @author Adohp
 * @version v1.0
 * @since 2017/9/22
 */
public class SimpleTest {

    @Test
    public void byteTest() {
        String s = "1";
        System.out.println(Arrays.toString(s.getBytes()));
    }

    @Test
    public void testLong() {
        System.out.println(Long.MAX_VALUE);
        System.out.println(Long.MAX_VALUE / 60 / 24 / 365);
    }

    @Test
    public void test() {
        int[] arr = {1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048};
        int i1 = 1470, i2 = 1982;
//        System.out.println(arithmetic(7));
//        arithmetic(i2);
        String loginId = UUID.randomUUID().toString();
        System.out.println(loginId);
    }

    public List<Integer> arithmetic(int val) {
        List<Integer> r = new ArrayList<>();
        char[] chars = Integer.toBinaryString(val).toCharArray();
        int length = chars.length;
        for (int i = 0; i < length; i++) {
            char c = chars[i];
            if (c == '1') {
                if (i == length - 1) {
                    r.add(1);
                } else {
                    r.add(2 << (length - i - 2));
                }
            }
        }
        return r;
    }
}