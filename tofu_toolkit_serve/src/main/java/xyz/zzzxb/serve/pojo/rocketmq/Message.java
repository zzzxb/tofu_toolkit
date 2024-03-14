package xyz.zzzxb.serve.pojo.rocketmq;

import lombok.Data;

/**
 * zzzxb
 * 2024/3/13
 */
@Data
public class Message {
    private String message;
    private String topic;
    private String tags;
    private String uuid;
}
