package com.oneapm.bi.loadStressor;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.PostConstruct;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class Counter {

    @Getter
    private final AtomicLong counter   = new AtomicLong(0);

    private final Timer      scheduler = new Timer();

    @PostConstruct
    public void setUp() {
        scheduler.schedule(new TimerTask() {

            @Override
            public void run() {
                log.info("Counter :" + counter.get());
            }
        }, 1000);
    }
}
