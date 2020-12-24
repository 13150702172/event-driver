package com.yangzinan.eventdemo.listener;

import com.yangzinan.eventdemo.event.EventInterface;

/**
 * 事件监听器接口
 */
public interface EventListener {
    public void trigger(EventInterface eventInterface);
}
