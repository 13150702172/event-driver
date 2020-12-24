package com.yangzinan.eventdemo.listener;

import com.yangzinan.eventdemo.center.EventDriverCenter;
import com.yangzinan.eventdemo.event.BankerEvent;
import com.yangzinan.eventdemo.event.EventInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


/**
 * 客户监听器
 */
@Component
public class ConsumerEventListener implements EventListener {

    Logger log = LoggerFactory.getLogger(ConsumerEventListener.class);

    @Autowired
    private EventDriverCenter eventDriverCenter;

    /**
     * 将事件和事件监听器注册到事件中心
     */
    @PostConstruct
    public void registry(){
        eventDriverCenter.registry(BankerEvent.class,this);
    }


    /**
     * 如果事件发布，将会触发该方法
     * @param eventInterface
     */
    @Override
    public void trigger(EventInterface eventInterface) {
        Integer number = eventInterface.getNumber();
        log.info("请{}客户到4号窗口办理业务",number);
    }


}
