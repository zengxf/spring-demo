package cn.zxf.spring_aop.super_test.srv;

import lombok.extern.slf4j.Slf4j;

/**
 * <br>
 * Created by ZXFeng on 2023/11/10
 */
@Slf4j
public abstract class BaseService {

    public void selectOne(int id) {
        log.info("BaseSrv -> select-one-id: [{}]", id);
    }

    public void selectList(int id) {
        log.info("BaseSrv -> select-list-id: [{}]", id);
    }

    public void updateOne(int id) {
        log.info("BaseSrv -> update-id: [{}]", id);
    }

}
