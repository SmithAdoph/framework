package com.adoph.framework.test.design.pattern.observer.v1;

/**
 * TODO
 *
 * @author Adoph
 * @version v1.0
 * @since 2018/3/21
 */
public class Parent implements Runnable {

    private Child c;

    public Parent(Child c) {
        this.c = c;
    }

    void feed() {
        while (true) {
            if (c.isWakenUp()) {
                System.out.println("父母给孩子喂奶！");
                break;
            }
        }
    }

    @Override
    public void run() {
        feed();
    }
}
