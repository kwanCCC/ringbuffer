package com.oneapm.bi.loadStressor.consumer;

import java.util.concurrent.Callable;

import lombok.Getter;

import com.oneapm.bi.loadStressor.ringbuffer.RingBuffer;
import com.oneapm.bi.loadStressor.ringbuffer.Strategy;

public class _Consumer implements Callable<String> {

    @Getter
    private RingBuffer<String> buffer;

    @Override
    public String call() throws Exception {
        return buffer.get(Strategy.YIELD);
    }

    public _Consumer(RingBuffer<String> buffer) {
        this.buffer = buffer;
    }
}
