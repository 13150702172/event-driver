package com.yangzinan.eventdemo;

import com.yangzinan.eventdemo.event.BankerEvent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
public class EventDemoApplication {

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext applicationContext = SpringApplication.run(EventDemoApplication.class, args);
        EventSender eventSender = applicationContext.getBean(EventSender.class);
        while (true){
            long orderId = ThreadLocalRandom.current().nextLong();
            eventSender.send(new BankerEvent());
            Thread.sleep(ThreadLocalRandom.current().nextLong(1000, 10000));
        }
    }

}
