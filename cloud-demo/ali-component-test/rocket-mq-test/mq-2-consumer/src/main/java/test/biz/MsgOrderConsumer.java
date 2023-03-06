package test.biz;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import test.config.SerConfig;

/**
 * <br/>
 * Created by ZXFeng on 2023/3/6.
 */
@RocketMQMessageListener(
        consumerGroup = "${rocketmq.consumer.group}",
        topic = "test-order-1"
        // , selectorExpression = "test1 || tag3"
        , consumeMode = ConsumeMode.ORDERLY
)
@Component
@Slf4j
public class MsgOrderConsumer implements RocketMQListener<String> {

    @Autowired
    private SerConfig config;

    @Override
    public void onMessage(String message) {
        log.info("[{}] 接收到消息！msg: [{}]", config.getSign(), message);
    }

}
