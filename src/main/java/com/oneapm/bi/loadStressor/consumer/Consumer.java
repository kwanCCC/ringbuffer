package com.oneapm.bi.loadStressor.consumer;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import lombok.Getter;

import com.oneapm.bi.loadStressor.ringbuffer.RingBuffer;
import com.oneapm.bi.loadStressor.ringbuffer.Strategy;

public class Consumer implements Future<String> {

    @Getter
    private RingBuffer<String> buffer;

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }

    @Override
    public boolean isCancelled() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isDone() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public String get() throws InterruptedException, ExecutionException {
        return buffer.get(Strategy.BLOCKING);
    }

    @Override
    public String get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return buffer.get(Strategy.BLOCKING);
    }

    public Consumer(RingBuffer<String> buffer) {
        this.buffer = buffer;
    }
}
