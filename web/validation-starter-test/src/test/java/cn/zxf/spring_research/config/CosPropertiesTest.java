package cn.zxf.spring_research.config;

import cn.zxf.spring_research.BaseAppTest4;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class CosPropertiesTest extends BaseAppTest4 {

    @Autowired
    CosProperties prop;

    @Test
    public void test() {
        log.info("prop: [{}]", prop);
    }

}