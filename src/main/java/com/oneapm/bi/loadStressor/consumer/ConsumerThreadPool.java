package com.oneapm.bi.loadStressor.consumer;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.lang3.StringUtils;

import com.oneapm.bi.loadStressor.ringbuffer.RingBuffer;

public class ConsumerThreadPool implements Runnable {

    private static final ExecutorService service = Executors.newFixedThreadPool(4, new ConsumerFactory());

    private final RingBuffer<String>     buffer;

    private final AtomicLong             counter;

    public ConsumerThreadPool(RingBuffer<String> buffer, AtomicLong count) {
        this.buffer = buffer;
        this.counter = count;
    }

    @Override
    public void run() {
        while (!buffer.isEmpty()) {
            _Consumer _consumer = new _Consumer(buffer);
            Future<String> submit = service.submit(_consumer);
            try {
                String string = submit.get();
                if (StringUtils.isNotBlank(string)) {
                    counter.addAndGet(1);
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            System.out.println();
        }
    }
}
