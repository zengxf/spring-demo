package test.biz;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import test.BaseSeataMainAppTest5;

/**
 * <br/>
 * Created by ZXFeng on 2023/3/2.
 */
@Slf4j
class UserServiceTest extends BaseSeataMainAppTest5 {

    @Autowired
    UserService service;

    @Test
    public void testOK() {
        service.operate(1, 1, 10);
    }

}