package com.oneapm.bi.loadStressor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.PostConstruct;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

import com.oneapm.bi.loadStressor.consumer.ConsumerThreadPool;
import com.oneapm.bi.loadStressor.producer.ProducerThreadPool;
import com.oneapm.bi.loadStressor.ringbuffer.RingBuffer;

@Slf4j
@Component
public class Config {

    private static final ExecutorService service = Executors.newFixedThreadPool(2);
    private final RingBuffer<String>     buffer  = new RingBuffer<String>(32768, 10);
    private final AtomicLong             counter = new AtomicLong(0);

    @PostConstruct
    public void setup() {
        ConsumerThreadPool consumerThreadPool = new ConsumerThreadPool(buffer, counter);
        ProducerThreadPool producerThreadPool = new ProducerThreadPool(buffer);
        while (true) {
            service.execute(producerThreadPool);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            service.execute(consumerThreadPool);
            log.info(" | " + counter.get());
            log.info(" | " + buffer.curSize());
        }
    }
}
