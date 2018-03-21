package com.adoph.framework.test.design.pattern.observer.v1;

/**
 * TODO
 *
 * @author Adoph
 * @version v1.0
 * @since 2018/3/21
 */
public class Child implements Runnable {

    /**
     * 是否醒了
     */
    private boolean isWakenUp = false;

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
        setWakenUp(true);
        System.out.println("小孩醒了！");
    }

    public synchronized boolean isWakenUp() {
        return isWakenUp;
    }

    public void setWakenUp(boolean wakenUp) {
        isWakenUp = wakenUp;
    }
}
