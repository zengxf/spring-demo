package cn.zxf.jdbc_transaction.test.inherit;

import cn.zxf.jdbc_transaction.test.user.User;
import cn.zxf.jdbc_transaction.test.user.UserDao;
import cn.zxf.jdbc_transaction.test.user_log.UserLog;
import cn.zxf.jdbc_transaction.test.user_log.UserLogDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <br>
 * Created by ZXFeng on 2023/9/5
 */
@Component
@Slf4j
public class BizAService extends BaseService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserLogDao logDao;

    /*** 继承的事务默认不会回滚"检测异常" */
    public void saveWithErr(User user, UserLog uLog) throws Exception {
        log.info("user: [{}]", user);
        userDao.save2(user);

        this.err();

        log.info("user-log: [{}]", uLog);
        logDao.save(uLog);
    }

    /*** 继承的事务默认会回滚"运行时异常" */
    public void saveWithRunErr(User user, UserLog uLog) {
        log.info("user: [{}]", user);
        userDao.save2(user);

        this.runErr();

        log.info("user-log: [{}]", uLog);
        logDao.save(uLog);
    }

    private void err() throws Exception {
        throw new Exception("主动报错");
    }

    private void runErr() {
        throw new RuntimeException("主动报错");
    }

}
