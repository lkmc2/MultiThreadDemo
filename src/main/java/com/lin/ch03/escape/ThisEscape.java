package com.lin.ch03.escape;

import com.lin.ch03.escape.event.Event;
import com.lin.ch03.escape.event.EventListener;
import com.lin.ch03.escape.event.EventSource;

/**
 * 引用逸出的例子
 * @author lkmc2
 * @date 2019/8/10 17:50
 */
public class ThisEscape {

    public ThisEscape(EventSource source) {
        source.registerListener(new EventListener() {
            public void onEvent(Event event) {
                doSomething(event);
            }
        });
    }

    private void doSomething(Event event) {
        System.out.println(event);
    }

}
