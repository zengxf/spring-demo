package cn_zxf_test.config.annotation;

import lombok.extern.slf4j.Slf4j;

/**
 * <br>
 * Created by ZXFeng on 2023/5/20
 */
@NewService
@Slf4j
public class MyTest1Service {

    public MyTest1Service() {
        log.info("进入 1");
    }

}
