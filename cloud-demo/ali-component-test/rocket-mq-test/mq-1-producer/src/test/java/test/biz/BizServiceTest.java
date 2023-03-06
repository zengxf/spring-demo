package test.biz;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import test.BaseMqProducerAppTest5;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CountDownLatch;

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
        service.sendMsg("test-1", "test-common-1111-" + now);
    }

    @Test
    public void sendMsg10() {
        int num = 10;
        for (int i = 0; i < num; i++) {
            String now = LocalTime.now().format(DateTimeFormatter.ofPattern("HH-mm-ss-SSS"));
            service.sendMsg("test-1", "test-common-10-xxx-##-" + now);
        }
    }

    @Test
    public void sendMsg10000() {
        long start = System.currentTimeMillis();
        int num = 10000; // 用时：[5433] ms / [5615] ms / [5710] ms
        for (int i = 0; i < num; i++) {
            String now = LocalTime.now().format(DateTimeFormatter.ofPattern("HH-mm-ss-SSS"));
            service.sendMsg("test-1", "test-common-1w-1111-##-" + now);
        }
        long use = System.currentTimeMillis() - start;
        log.info("发送 [{}] 条数据！用时：[{}] ms", num, use);
    }

    @Test
    @SneakyThrows
    public void sendOrderMsg10() {
        long start = System.currentTimeMillis();
        int num = 10;
        String tag = "test1"; // @RocketMQMessageListener(selectorExpression = "test1 || tag3")
        CountDownLatch cdl = new CountDownLatch(num);
        for (int i = 100; i < 100 + num; i++) {
            String now = LocalTime.now().format(DateTimeFormatter.ofPattern("HH-mm-ss"));
            String msg = "test-order---[" + i + "]---" + now;
            service.sendOrderMsg("test-order-1", tag, msg, i, () -> {
                cdl.countDown();
            });
        }
        long use = System.currentTimeMillis() - start;
        log.info("发送 [{}] 条数据！用时：[{}] ms", num, use);

        cdl.await();
        use = System.currentTimeMillis() - start;
        log.info("发送 [{}] 条数据！回调后用时：[{}] ms", num, use);
    }

    @Test
    public void sendBroad() {
        long start = System.currentTimeMillis();
        int num = 10;
        for (int i = 0; i < num; i++) {
            String now = LocalTime.now().format(DateTimeFormatter.ofPattern("HH-mm-ss"));
            String msg = "test-broad---[" + i + "]---" + now;
            service.sendBroad("test-broad-1", msg);
        }
        long use = System.currentTimeMillis() - start;
        log.info("发送 [{}] 条数据！用时：[{}] ms", num, use);
    }

    @Test
    public void sendDelay() {
        long start = System.currentTimeMillis();
        int num = 10;
        for (int i = 0; i < num; i++) {
            String now = LocalTime.now().format(DateTimeFormatter.ofPattern("HH-mm-ss"));
            String msg = "test-delay---[" + i + "]---" + now;
            service.sendDelay("test-1", msg, 5);
        }
        long use = System.currentTimeMillis() - start;
        log.info("发送 [{}] 条数据！用时：[{}] ms", num, use);
    }

}