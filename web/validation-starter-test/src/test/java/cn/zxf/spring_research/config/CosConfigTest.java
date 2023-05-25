package cn.zxf.spring_research.config;

import cn.zxf.spring_research.BaseAppTest4;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class CosConfigTest extends BaseAppTest4 {

    @Autowired
    CosPropertiesBck prop;
    @Autowired
    String secretId;

    @Test
    public void test() {
        log.info("prop-bck: [{}]", prop);
        log.info("secretId: [{}]", secretId);
    }

}