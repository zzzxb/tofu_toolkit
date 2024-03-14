package xyz.zzzxb.tofu.rocketmq.simple;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.junit.Test;

/**
 * zzzxb
 * 2024/3/13
 */
@Slf4j
public class ProducerTests {

    @Test
    public void testSend() throws MQClientException, InterruptedException {
        SimpleProducer producer = new ProducerBootstrap("localhost:9876", "testInfo").start();
        producer.asyncSend("zzzxb", "*", "你好", new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.info("发送成功, {}", sendResult);
            }

            @Override
            public void onException(Throwable throwable) {
                log.error("发送失败", throwable);
            }
        });
        Thread.sleep(1000);
    }
}
