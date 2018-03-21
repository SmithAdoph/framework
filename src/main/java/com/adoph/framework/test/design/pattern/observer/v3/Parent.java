package com.adoph.framework.test.design.pattern.observer.v3;

/**
 * TODO
 *
 * @author Adoph
 * @version v1.0
 * @since 2018/3/21
 */
public class Parent implements EventListener {
    @Override
    public void doAction(Event e) {
        System.out.println("父亲给孩子喂奶！");
    }
}
