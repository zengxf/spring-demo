package test.biz;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * <br/>
 * Created by ZXFeng on 2021/12/30.
 */
@Component
@Slf4j
public class AsyncMethodService {

    @Async
    public void print() {
        log.info("--------- print 00000 --------");
    }

    @Async("asyncPoolTaskExecutor1") // 指定用哪个线程池
    public void printByPool1() {
        log.info("--------- print 11111 --------");
    }

    @Async("asyncPoolTaskExecutor2")
    public void printByPool2() {
        log.info("--------- print 22222 --------");
    }

    @Async
    public void error() {
        log.info("--------- error 00000 --------");
        throw new RuntimeException("出错！");
    }

}
