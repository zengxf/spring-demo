package test.biz;

import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class OrderService {

    @Autowired
    private OrderDao dao;

    @Transactional(rollbackFor = Exception.class)
    public void operate(int opSign, int uid, int addMoney) {
        log.info("进入 Order -------------------");
        log.info("sign: [{}], uid: [{}], add-money: [{}]", opSign, uid, addMoney);
        dao.addMoney(uid, addMoney);
    }

}
