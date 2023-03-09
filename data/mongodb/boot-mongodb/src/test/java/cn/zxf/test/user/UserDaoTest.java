package cn.zxf.test.user;

import cn.zxf.test.BaseAppTest5;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class UserDaoTest extends BaseAppTest5 {

    @Autowired
    private UserDao dao;

    @Test
    public void batchInsert10000() {
        int num = 10000; // 用时 [354] ms [343] ms [348] ms

        long start = System.currentTimeMillis();

        List<User> list = new ArrayList<>(num);
        for (int i = 0; i < num; i++) {
            User user = new User();
            user.setUid(1000L + i);
            user.setUserName("test-" + i);
            user.setPassWord("pwd-" + i);
            list.add(user);
        }
        dao.batchInsert(list);

        long use = System.currentTimeMillis() - start;

        log.info("插入 [{}] 条数据，用时 [{}] ms", num, use);
    }

    @Test
    public void save10000() {
        int num = 10000; // 用时 [1794] ms [1962] ms [2027] ms

        long start = System.currentTimeMillis();

        for (int i = 0; i < num; i++) {
            User user = new User();
            user.setUid(1000L + i);
            user.setUserName("test-" + i);
            user.setPassWord("pwd-" + i);
            dao.saveUser(user);
        }

        long use = System.currentTimeMillis() - start;

        log.info("插入 [{}] 条数据，用时 [{}] ms", num, use);
    }

    @Test
    public void testSaveUser() {
        User user = new User();
        user.setUid(2L);
        user.setUserName("小明");
        user.setPassWord("fffooo123");
        dao.saveUser(user);
        log.info("保存成功！id: [{}]", user.getId());
    }

    @Test
    public void findUserByUserName() {
        User user = dao.findUserByUserName("小明");
        log.info("user: [{}]", user);
    }

    @Test
    public void updateUser() {
        User user = new User();
        user.setUid(2L);
        user.setUserName("天空");
        user.setPassWord("fffxxxx");
        dao.updateUser(user);
    }

    @Test
    public void deleteUserById() {
        dao.deleteUserById(1L);
    }

}
