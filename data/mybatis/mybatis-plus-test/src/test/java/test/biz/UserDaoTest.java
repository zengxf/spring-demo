package test.biz;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import test.AbstractApplicationTest;

import java.util.List;

@Slf4j
public class UserDaoTest extends AbstractApplicationTest {

    @Autowired
    UserDao dao;

    @Test
    public void selectAll() {
        List<User> list = dao.selectList(null);
        log.info("list-size: [{}]", list.size());
        list.forEach(user -> {
            log.info("user: [{}]", user);
        });
    }

    @Test
    public void selectOne() {
        User user = dao.selectById(1);
        log.info("user: [{}]", user);
    }

    @Test
    public void selectByLambda() {
        List<User> list = dao.selectList(
                new QueryWrapper<User>()
                        .lambda()
                        .gt(User::getAge, 18)
        );
        log.info("list-size: [{}]", list.size());
        list.forEach(user -> {
            log.info("user: [{}]", user);
        });
    }

}
