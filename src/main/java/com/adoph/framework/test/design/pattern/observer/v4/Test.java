package com.adoph.framework.test.design.pattern.observer.v4;

import java.util.ArrayList;
import java.util.List;

/**
 * 模拟AWT事件监听实现
 *
 * @author Adoph
 * @version v1.0
 * @since 2018/3/22
 */
public class Test {
    public static void main(String[] args) {
        Button b = new Button();
        b.addActionListener(new PressActionListener());
        b.press();
    }
}

class Button {

    /**
     * 拥有事件监听集合
     */
    private List<ActionListener> actionListeners = new ArrayList<>();

    public void addActionListener(ActionListener actionListener) {
        actionListeners.add(actionListener);
    }

    public void press() {
        ActionEvent e = new ActionEvent(System.currentTimeMillis(), this);
        for (ActionListener actionListener : actionListeners) {
            actionListener.actionPerformed(e);
        }
    }
}

/**
 * 事件监听
 */
interface ActionListener {
    void actionPerformed(ActionEvent e);
}

class PressActionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("press button!");
    }
}

/**
 * 事件
 */
class ActionEvent {
    /**
     * 什么时候发生
     */
    private long when;

    /**
     * 事件源
     */
    private Object source;

    public ActionEvent(long when, Object source) {
        this.when = when;
        this.source = source;
    }

    public long getWhen() {
        return when;
    }

    public Object getSource() {
        return source;
    }
}
