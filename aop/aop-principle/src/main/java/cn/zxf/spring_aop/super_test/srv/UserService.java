package cn.zxf.spring_aop.super_test.srv;

import cn.zxf.spring_aop.super_test.ann.DynamicDS;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * <br>
 * Created by ZXFeng on 2023/11/10
 */
@Component
@Slf4j
public class UserService extends BaseService {

    @DynamicDS("test-0000")
    public void test1() {
        log.info("test1...");
    }

    @Override
    public void selectOne(int id) {
        log.info("UserSrv -> select-one-id: [{}]", id);
    }

}
