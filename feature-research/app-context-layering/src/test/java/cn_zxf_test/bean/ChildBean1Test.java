package cn_zxf_test.bean;

import cn_zxf_test.layering.bean.ChildBean;
import cn_zxf_test.layering.context.ChildContext1;
import cn_zxf_test.layering.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <p/>
 * Created by ZXFeng on 2024/1/23
 */
@SpringBootTest(classes = {RootContext.class, ChildContext1.class}, webEnvironment = SpringBootTest.WebEnvironment.NONE)
@RunWith(SpringRunner.class)
@Slf4j
public class ChildBean1Test {

    @Autowired
    ChildBean bean;

    @Test
    public void test() {
        log.info("bean: [{}]", bean);
    }

}
