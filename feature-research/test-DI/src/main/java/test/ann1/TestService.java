package test.ann1;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * <p/>
 * Created by ZXFeng on 2024/7/11
 */
@Component
@Slf4j
public class TestService {

    @Resource(name = "testImpl2")        // 只有一个实现类时，可以不设置 name 或 type
    // @Resource(type = TestImpl1.class)
    ITest obj;
    @Resource
    TestImpl1 testImpl; // type 可识别
    @Resource
    ITest testImpl2;    // name 可识别

    // @Autowired
    // @Qualifier("testImpl2")
    // ITest obj;
    // @Autowired
    // TestImpl1 testImpl; // type 可识别
    // @Autowired
    // ITest testImpl2;    // name 可识别

    @PostConstruct
    public void init() {
        log.info("---------------------------\n");
        log.info("obj: [{}]\n", obj.getClass());
        log.info("testImpl: [{}]\n", testImpl.getClass());
        log.info("testImpl2: [{}]\n", testImpl2.getClass());
        log.info("---------------------------");
    }

}
