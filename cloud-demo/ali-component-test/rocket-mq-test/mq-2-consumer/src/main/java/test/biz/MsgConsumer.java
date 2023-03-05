package test.biz;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * <br/>
 * Created by ZXFeng on 2023/3/5.
 */
@RocketMQMessageListener(
        topic = "${rocketmq.consumer.topic}",
        consumerGroup = "${rocketmq.consumer.group}"
)
@Component
@Slf4j
public class MsgConsumer implements RocketMQListener<String> {

    @Override
    public void onMessage(String message) {
        log.info("接收到消息！msg: [{}]", message);
    }

}
