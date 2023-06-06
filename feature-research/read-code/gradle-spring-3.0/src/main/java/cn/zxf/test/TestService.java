package cn.zxf.test;

import lombok.extern.slf4j.Slf4j;

/**
 * <br>
 * Created by ZXFeng on 2023/6/6
 */
@Slf4j
public class TestService {

    public String test(String name) {
        log.info("hello " + name);
        return "success";
    }

}
