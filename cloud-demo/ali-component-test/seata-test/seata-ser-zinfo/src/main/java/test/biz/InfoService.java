package test.biz;

import io.seata.spring.annotation.GlobalTransactional;
import io.seata.tm.api.transaction.Propagation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class InfoService {

    @Autowired
    private InfoDao dao;

    @GlobalTransactional(
            name = "info-operate",
            // timeoutMills = 3000,
            timeoutMills = 300000,
            rollbackFor = Exception.class,
            propagation = Propagation.REQUIRED
    )
    public void operate(int opSign, int uid, int addMoney) {
        log.info("进入 Info -------------------");
        log.info("sign: [{}], uid: [{}], add-money: [{}]", opSign, uid, addMoney);
        int logSign = dao.logInfo(uid, addMoney);
        log.info("log-sign: [{}]", logSign);
        log.info("end ----------------------");

        if (opSign == 3) {
            throw new RuntimeException("sign = 1, err");
        } else if (opSign == 6) {
            try {
                Thread.sleep(4000L);
            } catch (InterruptedException e) {
                log.error("Interrupted!", e);
            }
        } else if (opSign == 8) {
            Thread.currentThread().stop();
        }
    }

}
