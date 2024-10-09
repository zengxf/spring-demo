package cn_zxf_test.user;

import cn_zxf_test.BaseTester5;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Slf4j
class UserRepositoryTest extends BaseTester5 {

    @Autowired
    UserRepository repos;

    @Test
    @Order(1)
    @Disabled
    void initData() {
        repos.deleteAll();

        for (int i = 1; i <= 5; i++) {
            User user = new User()
                    .setEmail("test-" + i + "@ss.cc")
                    .setMobile("1325656898" + i)
                    .setStatus(i % 2);
            repos.save(user);
        }
    }

    @Test
    @Order(2)
    void findAll() {
        List<User> list = repos.findAll();
        list.forEach(user -> { //
            log.info("user: [{}]", user);
        });
    }

    @Test
    @Order(3)
    void login() {
        User user = repos.login("13256568981");
        log.info("user: [{}]", user);
    }

    @Test
    @Order(4)
    void findStatus() {
        Integer userStatus = repos.findStatus(10);
        log.info("userStatus: [{}]", userStatus);
    }

}