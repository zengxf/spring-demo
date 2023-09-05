package cn.zxf.jdbc_transaction.test.inherit;

import cn.zxf.jdbc_transaction.test.user.User;
import cn.zxf.jdbc_transaction.test.user_log.UserLog;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class BizAServiceTest {

    @Autowired
    BizAService service;

    @Test(expected = Exception.class)
    public void saveWithErr() throws Exception {
        User user = User.builder()
                .name("test")
                .age(24)
                .build();

        UserLog userLog = UserLog.builder()
                .msg("事务-继承测试")
                .userId(11L)
                .build();

        service.saveWithErr(user, userLog);
    }

    @Test(expected = RuntimeException.class)
    public void saveWithRunErr() {
        User user = User.builder()
                .name("test*555")
                .age(66)
                .build();

        UserLog userLog = UserLog.builder()
                .msg("事务-继承测试")
                .userId(66L)
                .build();

        service.saveWithRunErr(user, userLog);
    }

}