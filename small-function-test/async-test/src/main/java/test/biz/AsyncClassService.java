package test.biz;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * <br/>
 * Created by ZXFeng on 2021/12/30.
 */
@Async
@Component
@Slf4j
public class AsyncClassService {

    public void print() {
        log.info("--------- print xxx --------");
    }

    public void error() {
        log.info("--------- error xxx --------");
        throw new RuntimeException("出错！");
    }

}
