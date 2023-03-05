package test.biz;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import test.BaseMqProducerAppTest5;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * <br/>
 * Created by ZXFeng on 2023/3/5.
 */
@Slf4j
class BizServiceTest extends BaseMqProducerAppTest5 {

    @Autowired
    BizService service;

    @Test
    public void sendMsg() {
        String now = LocalTime.now().format(DateTimeFormatter.ofPattern("HH-mm-ss"));
        service.sendMsg("test-1", "test-1111-" + now);
    }

}