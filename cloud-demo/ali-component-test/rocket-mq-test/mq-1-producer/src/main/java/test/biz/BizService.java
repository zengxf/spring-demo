package test.biz;


import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;

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

}
