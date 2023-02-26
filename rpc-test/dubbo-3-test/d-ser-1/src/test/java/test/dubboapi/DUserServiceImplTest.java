package test.dubboapi;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import test.BaseDSerAppTest5;
import test.dubbo.api.DUserDTO;

import java.util.List;

/**
 * <br/>
 * Created by ZXFeng on 2023/2/25.
 */
@Slf4j
class DUserServiceImplTest extends BaseDSerAppTest5 {

    @Autowired
    DUserServiceImpl service;

    @Test
    void getUser() {
        DUserDTO user = service.getUser("fa");
        log.info("user: [{}]", user);
    }

    @Test
    void getUsers() {
        List<DUserDTO> users = service.getUsers(5);
        users.forEach(user -> {
            log.info("user: [{}]", user);
        });
    }

}