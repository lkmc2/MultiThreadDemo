package com.lin.ch03.escape.event;



/**
 * 事件监听器
 * @author lkmc2
 * @date 2019/8/10 17:52
 */
public interface EventListener {
    /**
     * 执行事件
     * @param event 事件
     */
    void onEvent(Event event);
}
