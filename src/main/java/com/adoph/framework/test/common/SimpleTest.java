package com.adoph.framework.test.common;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 简单测试
 *
 * @author Adohp
 * @version v1.0
 * @since 2017/9/22
 */
public class SimpleTest {

    @Test
    public void testStringReg() {
        String sql = "select * from table";
        String _sql = sql;
        String pattern = "\\?";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(sql);
        int count = 0;
        while (m.find()) {
            _sql = _sql.replaceFirst(pattern, "#" + count);
            count++;
        }
        _sql = _sql.replaceAll("#", "\\?");
        System.out.println(_sql.trim());
    }

    @Test
    public void getLoggerTest() {
        Logger logger1 = LoggerFactory.getLogger(getClass());
        Logger logger2 = LoggerFactory.getLogger(getClass());
        Assert.assertEquals(logger1, logger2);
    }

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