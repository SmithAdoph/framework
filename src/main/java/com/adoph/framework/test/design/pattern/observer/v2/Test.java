package com.adoph.framework.test.design.pattern.observer.v2;

/**
 * TODO
 *
 * @author Adoph
 * @version v1.0
 * @date 2018/3/21
 */
public class Test {

    public static void main(String[] args) {
        Parent p = new Parent();
        Child c = new Child(p);
        new Thread(c).start();
    }

}
