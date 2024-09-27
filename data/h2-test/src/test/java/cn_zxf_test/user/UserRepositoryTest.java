package cn_zxf_test.user;

import cn_zxf_test.BaseTester5;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class UserRepositoryTest extends BaseTester5 {

    @Autowired
    UserRepository repos;

    @Test
    void initData() {
        User user = new User()
                .setEmail("test-zz@ss.cc")
                .setMobile("13256568989")
                .setStatus(1);
        repos.save(user);
    }

    @Test
    void login() {
    }

    @Test
    void findStatus() {
    }

}