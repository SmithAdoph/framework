package com.adoph.framework.test.proxy;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.junit.Test;
import java.lang.reflect.Method;
import net.sf.cglib.proxy.Enhancer;

/**
 * 基于cglib的动态代理
 *
 * @author Adoph
 * @version v1.0
 * @since 2018/2/7
 */
public class CGLIBProxy {

    @Test
    public void test() {
        System.out.println("----------------cglib动态代理----------------");
        System.out.println();
        DriverCglibProxy driverCglibProxy = new DriverCglibProxy();
        Driver o = (Driver) driverCglibProxy.getProxyInstance(new Driver());
        o.drive();
    }
}

class DriverCglibProxy implements MethodInterceptor {

    public Object getProxyInstance(Object target) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);  // call back method
        enhancer.setClassLoader(target.getClass().getClassLoader());
        return enhancer.create();  // create proxy instance
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("代理类调用开始！！！");
        Object result = methodProxy.invokeSuper(o, objects);
        System.out.println("代理类调用结束！！！");
        return result;
    }
}

class Car {
    public void call() {
        System.out.println("滴滴滴滴...........");
        print();
    }
    public void print() {
        System.out.println("333333333333333./.............");
    }
}
