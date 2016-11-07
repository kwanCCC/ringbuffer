package com.oneapm.bi.loadStressor.producer;

import com.oneapm.bi.loadStressor.StressThreadFactory;

public class ProducerThreadFactory extends StressThreadFactory {

    public ProducerThreadFactory() {
        super("Producer");
    }
}
