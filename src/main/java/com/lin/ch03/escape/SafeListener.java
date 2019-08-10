package com.lin.ch03.escape;

import com.lin.ch03.escape.event.Event;
import com.lin.ch03.escape.event.EventListener;
import com.lin.ch03.escape.event.EventSource;

/**
 * 使用工厂方法防止this引用在构造过程中逸出
 * @author lkmc2
 * @date 2019/8/10 17:58
 */
public class SafeListener {

    private final EventListener listener;

    private SafeListener() {
        this.listener = new EventListener() {
            public void onEvent(Event event) {
                doSomething(event);
            }
        };
    }

    private void doSomething(Event event) {
        System.out.println(event);
    }

    public static SafeListener newInstance(EventSource source) {
        SafeListener safe = new SafeListener();
        source.registerListener(safe.listener);
        return safe;
    }

}
