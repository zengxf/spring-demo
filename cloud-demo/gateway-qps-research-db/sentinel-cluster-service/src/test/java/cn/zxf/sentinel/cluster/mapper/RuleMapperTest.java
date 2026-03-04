package cn.zxf.sentinel.cluster.mapper;

import cn.zxf.sentinel.cluster.config.RulePO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * <p/>
 * Created by ZXFeng on 2026/3/2
 */
@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@RunWith(SpringRunner.class)
public class RuleMapperTest {

    @Autowired
    RuleMapper mapper;

    @Test
    public void selectAll() {
        List<RulePO> list = mapper.selectEnabledAll();
        log.info("list.size: {}", list.size());
        list.forEach(po -> log.info("po: {}", po));
    }

}
