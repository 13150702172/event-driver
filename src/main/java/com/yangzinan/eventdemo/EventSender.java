package com.yangzinan.eventdemo;

import com.yangzinan.eventdemo.center.EventDriverCenter;
import com.yangzinan.eventdemo.event.EventInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EventSender {

    @Autowired
    private EventDriverCenter eventDriverCenter;

    public void send(EventInterface eventInterface){
        eventDriverCenter.send(eventInterface);
    }

}
