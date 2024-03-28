package xyz.zzzxb.serve.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.zzzxb.serve.pojo.rocketmq.Message;
import xyz.zzzxb.serve.pojo.rocketmq.StartupInfo;
import xyz.zzzxb.tofu.common.pojo.RespMsg;
import xyz.zzzxb.tofu.common.util.CheckParamUtils;
import xyz.zzzxb.tofu.rocketmq.simple.SimpleProducer;
import xyz.zzzxb.tofu.rocketmq.simple.ProducerBootstrap;

import java.util.concurrent.ConcurrentHashMap;

/**
 * zzzxb
 * 2024/3/13
 */
@Slf4j
@RestController
@RequestMapping("/rocketmq")
@RequiredArgsConstructor
public class RocketMQController {
    private static final ConcurrentHashMap<String, SimpleProducer> producerMap = new ConcurrentHashMap<>();

    @PostMapping("/start")
    public synchronized RespMsg<Boolean> start(@RequestBody StartupInfo info) {
        if (producerMap.get(info.getUuid()) == null) {
            try {
                SimpleProducer producer = new ProducerBootstrap(info.getAddr(), info.getGroup()).start();
                producerMap.put(info.getUuid(), producer);
            } catch (Exception e) {
                log.error("rocketmq 启动失败", e);
                return new RespMsg<Boolean>().fail(400, "rocketmq 启动失败 - " + e.getMessage());
            }
        }
        return new RespMsg<Boolean>().success(200, "rocketmq 启动成功", true);
    }

    @PostMapping("/shutdown")
    public synchronized RespMsg<Boolean> shutdown(@RequestBody StartupInfo info) {
        SimpleProducer producer = producerMap.get(info.getUuid());
        if ( producer != null) {
            producer.shutdown();
        }
        return new RespMsg<Boolean>().success(200, "rocketmq 关闭成功", true);
    }

    @PostMapping("/send")
    public RespMsg<SendResult> sendMsg(@RequestBody Message message) {
        SimpleProducer producer = producerMap.get(message.getUuid());
        CheckParamUtils.isNull(producer).throwMessage("请先启动 rocketmq");
        SendResult sendResult = null;
        try {
            sendResult = producer.syncSend(message.getTopic(), message.getTags(), message.getMessage());
        }catch (Exception e) {
            log.error("rocketmq 发送信息失败", e);
            return new RespMsg<SendResult>().fail(400, "rocketmq 发送信息失败 - " + e.getMessage());
        }
        return new RespMsg<SendResult>().success(200, "rocketmq 发送信息成功", sendResult);
    }
}
