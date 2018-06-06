package com.adoph.framework.test;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * 异常捕获测试
 *
 * @author Adoph
 * @version v1.0
 * @date 2018/3/2
 */
public class ExceptionTest {

    @Test
    public void testTryException() {
        try {
            System.out.println(1 / 0);
        } catch (NullPointerException e) {
            System.out.println("NullPointerException");
            e.printStackTrace();
        } catch (ArithmeticException e) {
            System.out.println("ArithmeticException");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Exception");
            e.printStackTrace();
        } finally {
            System.out.println("finally!!!");
        }
    }

    @Test
    public void testArrayToList() {
        Integer[] arr = {1, 2, 4};
        List list1 = Arrays.asList(1, 2, 4);
        List<Integer> list2 = Arrays.asList(arr);
        for(Object e : list1) {
            System.out.println(e);
        }
        System.out.println("-----------------");
        for(Object e : list2) {
            System.out.println(e);
        }
    }
}
