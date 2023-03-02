package test.biz;

import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserService {

    @Autowired
    private UserDao dao;

    @GlobalTransactional(timeoutMills = 3000, rollbackFor = Exception.class)
    public void operate(int opSign, int uid, int addMoney) {
        dao.addMoney(uid, addMoney);
    }

}
