package com.adoph.framework.test.design.pattern.observer.v3;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author Adoph
 * @version v1.0
 * @since 2018/3/21
 */
public class Child implements Runnable {

    private List<EventListener> listeners = new ArrayList<>();

    void addListener(EventListener listener) {
        listeners.add(listener);
    }

    @Override
    public void run() {
        try {
            Thread.sleep(5 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        wakeUp();
    }

    private void wakeUp() {
        for (EventListener listener : listeners) {
            listener.doAction(new WakenUpEvent(this));
        }
    }
}
