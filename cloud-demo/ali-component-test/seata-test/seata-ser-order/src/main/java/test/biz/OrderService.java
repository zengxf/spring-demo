package test.biz;

import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderService {

    @Autowired
    private OrderDao dao;

    // @Transactional(rollbackFor = Exception.class)
    @GlobalTransactional(
            name = "order-operate",
            timeoutMills = 3000,
            // timeoutMills = 300000,
            rollbackFor = Exception.class
    )
    public void operate(int opSign, int uid, int addMoney) {
        log.info("进入 Order -------------------");
        log.info("sign: [{}], uid: [{}], add-money: [{}]", opSign, uid, addMoney);
        int orderSign = dao.addMoney(uid, addMoney);
        log.info("order-sign: [{}]", orderSign);
        log.info("end ----------------------");

        if (opSign == 2) {
            throw new RuntimeException("sign = 1, err");
        } else if (opSign == 5) {
            try {
                Thread.sleep(4000L);
                // Thread.sleep(40000L);
            } catch (InterruptedException e) {
                log.error("Interrupted!", e);
            }
        } else if (opSign == 7) {
            Thread.currentThread().stop();
        }
    }

}
