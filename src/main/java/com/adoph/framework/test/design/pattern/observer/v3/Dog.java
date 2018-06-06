package com.adoph.framework.test.design.pattern.observer.v3;

/**
 * TODO
 *
 * @author Tangqiandong
 * @version v1.0
 * @date 2018/3/21
 */
public class Dog implements EventListener {
    @Override
    public void doAction(Event e) {
        System.out.println("Dog wang wang ...");
    }
}
