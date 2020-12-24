package com.yangzinan.eventdemo.center;


import com.yangzinan.eventdemo.event.EventInterface;
import com.yangzinan.eventdemo.listener.EventListener;
import org.w3c.dom.events.EventException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;

/**
 * 注册中心
 */
public class EventDriverCenter {

    //用于存储事件和监听器，用于映射
    private final ConcurrentHashMap<Class<?>, List<EventListener>> concurrentHashMap = new ConcurrentHashMap<>();

    //连接池
    private final Executor executor;

    public EventDriverCenter(Executor executor){
        this.executor = executor;
    }

    /**
     * 用于事件和监听器绑定
     * @param eventClass
     * @param eventListener
     */
    public void registry(Class eventClass,EventListener eventListener){
        //从HashMap中获取监听器
        List<EventListener> eventListeners = concurrentHashMap.get(eventClass);
        //如果为空，就实例化一个List
        if(eventListeners == null){
            eventListeners = new ArrayList<>();
        }
        //将监听器放入List中
        eventListeners.add(eventListener);
        //再将监听器list和事件进行绑定，这里可以理解，一个事件对应多个事件监听器（事件处理器）
        concurrentHashMap.put(eventClass,eventListeners);
    }

    /**
     * 发送事件
     * @param eventInterface
     */
    public void send(EventInterface eventInterface){
        //获取事件对应的监听器
        List<EventListener> eventListeners = concurrentHashMap.get(eventInterface.getClass());
        //如果事件未注册，就抛出异常
        if(eventListeners == null || eventListeners.size() == 0){
            throw new EventException((short)500,"事件未注册");
        }

        //如果事件进行注册，就需要执行任务，调用eventListener的trigger方法
        for(EventListener eventListener:eventListeners){
            executor.execute(()->eventListener.trigger(eventInterface));
        }
    }



}
