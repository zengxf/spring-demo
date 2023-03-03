package test.biz;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BizService {

    private int i = 0;

    public void hello() {
        log.info("== hello ==");
    }


    public void operate(int sign) {
        log.info("进入 Biz ----------------------");
        switch (sign) {
            case 1: {
                if (i++ % 2 == 0) {
                    log.info("主动出错！============");
                    throw new RuntimeException("主动出错！");
                } else {
                    log.info("正常 -----------------");
                }
            }
        }
        log.info("退出 Biz ----------------------");
    }

}
