package com.yangzinan.eventdemo.event;

public class BankerEvent implements EventInterface{

    private Integer number = 100;

    @Override
    public Integer getNumber() {
        return number;
    }
}
