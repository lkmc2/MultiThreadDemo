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

        /*
        当构造好了SafeListener对象（通过构造器构造）之后，我们才启动了监听线程，
        也就确保了SafeListener对象是构造完成之后再使用的SafeListener对象。

        具体来说，只有当构造函数返回时，this引用才应该从线程中逸出。
        构造函数可以将this引用保存到某个地方，只要其他线程不会在构造函数完成之前使用它。
         */
        source.registerListener(safe.listener);
        return safe;
    }

}
