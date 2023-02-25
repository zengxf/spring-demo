package test.dubboapi;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import test.BaseDSer1AppTest5;
import test.dubbo.api.DUserDTO;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <br/>
 * Created by ZXFeng on 2023/2/25.
 */
@Slf4j
class DUserServiceImplTest extends BaseDSer1AppTest5 {

    @Autowired
    DUserServiceImpl service;

    @Test
    @Disabled("Dubbo-2.7 在 JDK-17 下面测不了，加了 JVM 参数也没用")
    void getUser() {
        DUserDTO user = service.getUser("fa");
        log.info("user: [{}]", user);
    }

    @Test
    void getUsers() {
    }

}