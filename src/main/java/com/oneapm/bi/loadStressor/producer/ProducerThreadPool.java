package com.oneapm.bi.loadStressor.producer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.oneapm.bi.loadStressor.ringbuffer.RingBuffer;

public class ProducerThreadPool implements Runnable {

    private final RingBuffer<String> buffer;
    private final ExecutorService    service = Executors.newFixedThreadPool(16, new ProducerThreadFactory());


    public ProducerThreadPool(RingBuffer<String> buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (!buffer.isFull()) {
            Producer producer = new Producer(buffer);
            service.execute(producer);
        }
    }
}
