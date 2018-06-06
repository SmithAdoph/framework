package com.adoph.framework.test.design.pattern.observer.v1;

/**
 * TODO
 *
 * @author Adoph
 * @version v1.0
 * @date 2018/3/21
 */
public class Test {

    public static void main(String[] args) {
        Child c = new Child();
        Parent p = new Parent(c);
        new Thread(c).start();
        new Thread(p).start();
    }

}
