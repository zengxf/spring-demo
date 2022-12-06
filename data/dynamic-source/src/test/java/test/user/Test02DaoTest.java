package test.user;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import test.BaseTester5;

import java.util.List;

/**
 * <br/>
 * Created by ZXFeng on 2022/7/25.
 */
@Slf4j
public class Test02DaoTest extends BaseTester5 {

    @Autowired
    Test02Dao dao;

    @Test
    public void findAll() {
        List<User02> list = dao.findAll();
        log.info("list-size: [{}]", list.size());
        list.forEach(user -> log.info("user-01: [{}]", user));
    }

}