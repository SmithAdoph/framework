package com.adoph.framework.test.design.pattern.observer.v3;

/**
 * TODO
 *
 * @author Adoph
 * @version v1.0
 * @since 2018/3/21
 */
public class Test {

    public static void main(String[] args) {
        Child c = new Child();
        Parent p = new Parent();
        Dog d = new Dog();
        c.addListener(p);
        c.addListener(d);
        new Thread(c).start();
    }

}
