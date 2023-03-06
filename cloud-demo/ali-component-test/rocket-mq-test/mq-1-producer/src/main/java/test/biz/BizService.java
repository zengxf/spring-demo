package test.biz;


import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import java.util.UUID;

@Slf4j
@Component
public class BizService {

    @Autowired
    private RocketMQTemplate mqTemplate;

    @PostConstruct
    public void init() {
        log.info("== BizService init! ==");
    }

    public void sendMsg(String topic, String msg) {
        log.info("send-msg, topic: [{}], msg: [{}] ------------", topic, msg);

        mqTemplate.convertAndSend(topic, msg);

        log.info("send-msg, end -------------------------------");
    }

    public void sendOrderMsg(String topic, String tag, String msg, int orderId, Runnable call) {
        log.info("send-msg, topic: [{}:{}], msg: [{}], orderId: [{}] ------------", topic, tag, msg, orderId);
        // String destination = topic + ":" + tag; // @RocketMQMessageListener(selectorExpression = "tag || tag3")
        String destination = topic;
        mqTemplate.asyncSendOrderly(destination, msg, "order-" + orderId, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.info("msg-id: " + sendResult.getMsgId() + ": " + msg
                        + " , queueId: " + sendResult.getMessageQueue().getQueueId());
                call.run();
            }

            @Override
            public void onException(Throwable e) {
                log.error("发送出错！", e);
                call.run();
            }
        });
    }

    public void sendBroad(String topic, String msg) {
        log.info("send-msg, topic: [{}], msg: [{}] ------------", topic, msg);

        Message<String> msgBody = MessageBuilder.withPayload(msg).build();
        mqTemplate.send(topic, msgBody);

        log.info("send-msg, end -------------------------------");
    }

    public void sendDelay(String topic, String msg, int seconds) {
        log.info("send-msg, topic: [{}], msg: [{}] ------------", topic, msg);

        mqTemplate.syncSendDelayTimeSeconds(topic, msg, seconds);

        log.info("send-msg, end -------------------------------");
    }

    public void sendTrans(String topic, String msg) {
        log.info("send-msg, topic: [{}], msg: [{}] ------------", topic, msg);

        Message<String> msgBody = MessageBuilder.withPayload(msg).build();
        mqTemplate.sendMessageInTransaction(topic, msgBody, UUID.randomUUID().toString());

        log.info("send-msg, end -------------------------------");
    }

}
