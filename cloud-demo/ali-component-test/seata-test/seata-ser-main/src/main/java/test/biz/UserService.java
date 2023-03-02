package test.biz;

import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import test.feign.OrderFeign;

@Slf4j
@Component
public class UserService {

    @Autowired
    private UserDao dao;
    @Autowired
    private OrderFeign orderFeign;

    @GlobalTransactional(name = "user-operate", timeoutMills = 30000, rollbackFor = Exception.class)
    public void operate(int opSign, int uid, int addMoney) {
        log.info("进入 User -------------------");
        log.info("sign: [{}], uid: [{}], add-money: [{}]", opSign, uid, addMoney);

        int userSign = dao.addMoney(uid, addMoney);
        log.info("user-op-sign: [{}]", userSign);

        String orderRes = orderFeign.operate(opSign, uid, addMoney);
        log.info("order-res: [{}]", orderRes);
        
    }

}
