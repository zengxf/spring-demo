package test.biz;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * <br/>
 * Created by ZXFeng on 2024/1/3.
 */
@Slf4j
@Component
public class BizService {

    public void hello() {
        log.info("== hello ==");
    }

}
