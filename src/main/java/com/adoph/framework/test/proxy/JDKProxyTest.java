package com.adoph.framework.test.proxy;

import com.adoph.framework.test.annotation.MyTest;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 基于jdk的动态代理
 *
 * @author Adoph
 * @version v1.0
 * @since 2018/2/7
 */
public class JDKProxyTest {

    @Test
    public void test() {
        System.out.println("----------------jdk动态代理----------------");
        System.out.println();
        Drive o = (Drive) Proxy.newProxyInstance(getClass().getClassLoader(), new Class[]{Drive.class}, new DriverProxy(new Driver()));
        o.drive();
    }

}

class DriverProxy implements InvocationHandler {

    private Object target;

    DriverProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("代理类调用开始！！！");
        Object result = method.invoke(target, args);
        System.out.println("代理类调用结束！！！");
        return result;
    }
}

interface Drive {
    void drive();
}

@MyTest(name = "CarClass")
class Driver implements Drive {
    @MyTest(name = "car")
    private String text;

    public void drive() {
        System.out.println("老司机发车...");
        call();
    }

    public void call() {
        System.out.println("老司机开车打电话...");
    }
}