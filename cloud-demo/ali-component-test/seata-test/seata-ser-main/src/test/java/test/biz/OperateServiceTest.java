package test.biz;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import test.BaseSeataMainAppTest5;

/**
 * <br/>
 * Created by ZXFeng on 2023/3/2.
 */
@Slf4j
class OperateServiceTest extends BaseSeataMainAppTest5 {

    @Autowired
    OperateService service;

    @Test
    @Disabled("JDK17 启动不了，加不了参数")
    public void testOK() {
        service.operate(1, 1, 10);
    }

}