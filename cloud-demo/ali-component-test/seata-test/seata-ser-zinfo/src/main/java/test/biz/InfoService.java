package test.biz;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class InfoService {

    @Autowired
    private InfoDao dao;

    @Transactional(rollbackFor = Exception.class)
    public void operate(int opSign, int uid, int addMoney) {
        log.info("进入 Order -------------------");
        log.info("sign: [{}], uid: [{}], add-money: [{}]", opSign, uid, addMoney);
        dao.logInfo(uid, addMoney);
    }

}
