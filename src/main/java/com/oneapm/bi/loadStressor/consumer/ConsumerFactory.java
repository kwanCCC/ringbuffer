package com.oneapm.bi.loadStressor.consumer;

import com.oneapm.bi.loadStressor.StressThreadFactory;

public class ConsumerFactory extends StressThreadFactory {

    public ConsumerFactory() {
        super("consumer");
    }
}
