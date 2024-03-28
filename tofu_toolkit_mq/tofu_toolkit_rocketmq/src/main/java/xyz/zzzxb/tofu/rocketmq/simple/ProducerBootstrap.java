package xyz.zzzxb.tofu.rocketmq.simple;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;


/**
 * zzzxb
 * 2024/3/13
 */
@Slf4j
public class ProducerBootstrap {
    private final DefaultMQProducer producer;
    private boolean startState;

    public ProducerBootstrap(String addr, String producerGroup) {
        producer = new DefaultMQProducer(producerGroup);
        producer.setNamesrvAddr(addr);
        producer.setRetryTimesWhenSendAsyncFailed(0);
    }

    public SimpleProducer start() throws Exception {
        if (producer != null && !startState) {
            producer.start();
            return new SimpleProducer(producer);
        }
        throw new RuntimeException("service has already started");
    }
}

