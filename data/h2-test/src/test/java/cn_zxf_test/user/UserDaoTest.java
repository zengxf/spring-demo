package cn_zxf_test.user;

import cn_zxf_test.BaseTester5;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Slf4j
class UserDaoTest extends BaseTester5 {

    @Autowired
    UserDao dao;
    
    @Test
    void findList() {
        {
            List<User> list = dao.findList("5656");
            log.info("list-size: [{}]", list.size());
            for (User user : list) {
                log.info("user: [{}]", user);
            }
        }

        {
            List<User> list = dao.findList("est-");
            log.info("list-size: [{}]", list.size());
            for (User user : list) {
                log.info("user: [{}]", user);
            }
        }
    }
    
}