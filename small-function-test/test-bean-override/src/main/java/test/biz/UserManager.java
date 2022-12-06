package test.biz;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * <br/>
 * Created by ZXFeng on 2022/6/1.
 */
@Component
@Slf4j
public class UserManager {

    private String userName;

    public UserManager() {
        log.info("无参 ----");
    }

    public UserManager(String userName) {
        this.userName = userName;
        log.info("有参 ---- [{}]", userName);
    }

}
