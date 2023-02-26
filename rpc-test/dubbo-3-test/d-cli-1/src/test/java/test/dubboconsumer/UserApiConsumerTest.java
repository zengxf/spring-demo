package test.dubboconsumer;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import test.BaseDCliAppTest5;
import test.dubbo.api.DUserDTO;

import java.util.List;
import java.util.stream.IntStream;

/**
 * <br/>
 * Created by ZXFeng on 2023/2/26.
 */
@Slf4j
class UserApiConsumerTest extends BaseDCliAppTest5 {

    @Autowired
    UserApiConsumer service;

    @Test
    void getUser() {
        DUserDTO user = service.getUser("fa");
        log.info("user: [{}]", user);
    }

    @Test
    void getNUser() {
        IntStream.rangeClosed(1, 10)
                .forEach(i -> {
                    DUserDTO user = service.getUser("fa-" + i);
                    log.info("user: [{}]", user);
                });
    }

    @Test
    void getUsers() {
        List<DUserDTO> users = service.getUsers(5);
        users.forEach(user -> {
            log.info("user: [{}]", user);
        });
    }

}