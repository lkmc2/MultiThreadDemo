package com.lin.ch03.escape.event;

/**
 * 事件源
 * @author lkmc2
 * @date 2019/8/10 17:51
 */
public class EventSource {

    private EventListener eventListener;

    /**
     * 注册事件监听器
     * @param eventListener 事件监听器
     */
    public void registerListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    /**
     * 获取事件监听器
     * @return 事件监听器
     */
    public EventListener getEventListener() {
        return eventListener;
    }

}
