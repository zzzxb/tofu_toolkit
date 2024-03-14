package xyz.zzzxb.tofu.rocketmq.simple;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import xyz.zzzxb.tofu.common.util.StringUtils;

import java.nio.charset.StandardCharsets;

@Slf4j
public class SimpleProducer {
    private final DefaultMQProducer producer;

    public SimpleProducer(DefaultMQProducer producer) {
        this.producer = producer;
    }

    public SendResult syncSend(String topic, String content) {
        return syncSend(topic, "*", content);
    }

    public SendResult syncSend(String topic, String tags, String content) {
        SendResult sendResult = null;
        try {
            Message msg = new Message(topic, StringUtils.isBlank(tags, "*"), content.getBytes(StandardCharsets.UTF_8));
            sendResult = producer.send(msg);
        } catch (Exception e) {
            log.error("rocketmq send message fail!", e);
            throw new RuntimeException(e);
        }
        return sendResult;
    }

    public void asyncSend(String topic, String content, SendCallback callback) {
        asyncSend(topic, "*", content, callback);
    }

    public void asyncSend(String topic, String tags, String content, SendCallback callback) {
        try {
            Message msg = new Message(topic, StringUtils.isBlank(tags, "*"), content.getBytes(StandardCharsets.UTF_8));
            producer.send(msg, callback);
        } catch (Exception e) {
            log.error("rocketmq send message fail!", e);
            throw new RuntimeException(e);
        }
    }

    public void shutdown() {
        producer.shutdown();
    }
}
