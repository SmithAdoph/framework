package com.adoph.framework.test.design.pattern.observer.v2;

/**
 * TODO
 *
 * @author Adoph
 * @version v1.0
 * @since 2018/3/21
 */
public class Child implements Runnable {

    private Parent p;

    public Child(Parent p) {
        this.p = p;
    }

    @Override
    public void run() {
        sleep();
    }

    void sleep() {
        System.out.println("小孩正在睡觉！");
        try {
            Thread.sleep(5 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("小孩醒了！");
        p.feed(this);
    }

}
