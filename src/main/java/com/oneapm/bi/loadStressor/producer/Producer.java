package com.oneapm.bi.loadStressor.producer;

import java.util.UUID;

import com.oneapm.bi.loadStressor.ringbuffer.RingBuffer;
import com.oneapm.bi.loadStressor.ringbuffer.Strategy;

public class Producer implements Runnable {

    private RingBuffer<String> buffer;
    private String             packData;

    public Producer(RingBuffer<String> buffer) {
        this.buffer = buffer;
        packData = UUID.randomUUID().toString();
    }

    @Override
    public void run() {
        buffer.add(packData, Strategy.YIELD);
    }
}
