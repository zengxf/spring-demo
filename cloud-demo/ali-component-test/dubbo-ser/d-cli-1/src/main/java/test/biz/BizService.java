package test.biz;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BizService {

    // @PostConstruct
    // public void init() {
    //     log.info("== BizService init! ==");
    // }

    public void hello() {
        log.info("== hello ==");
    }

}
