package com.adoph.framework.test.common;

import org.junit.Test;

import java.util.Arrays;

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
}