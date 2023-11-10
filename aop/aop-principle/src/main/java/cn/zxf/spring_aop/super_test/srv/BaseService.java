package cn.zxf.spring_aop.super_test.srv;

import lombok.extern.slf4j.Slf4j;

/**
 * <br>
 * Created by ZXFeng on 2023/11/10
 */
@Slf4j
public abstract class BaseService {

    public void selectOne(int id) {
        log.info("select-id: [{}]", id);
    }

    public void updateOne(int id) {
        log.info("update-id: [{}]", id);
    }

}
