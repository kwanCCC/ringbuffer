package com.oneapm.bi.loadStressor;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

import lombok.Getter;

public class StressThreadFactory implements ThreadFactory {

    @Getter
    private String        name;

    private AtomicInteger mark = new AtomicInteger(0);

    public StressThreadFactory(String name) {
        this.name = name;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        thread.setName(name + "------" + mark.addAndGet(1));
        return thread;
    }
}
