package com.yangzinan.eventdemo.config;

import com.yangzinan.eventdemo.center.EventDriverCenter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

@Configuration
public class EventCenterConfig {

    @Bean
    public EventDriverCenter eventDriverCenter(){
        ThreadFactory namedThreadFactory = Executors.defaultThreadFactory();
        int corePoolSize = Runtime.getRuntime().availableProcessors();
        int maximumPoolSize = corePoolSize * 2;
        // 创建一个线程池
        Executor pool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, 10L,
                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(1024),
                namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
        return new EventDriverCenter(pool);
    }

}
