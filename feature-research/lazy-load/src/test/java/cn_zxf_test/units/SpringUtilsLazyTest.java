package cn_zxf_test.units;

import cn_zxf_test.BaseLazyTester5;
import cn_zxf_test.biz.BizService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class SpringUtilsLazyTest extends BaseLazyTester5 {

    @Autowired // 声明一下，才初始化
    SpringUtils springUtils;

    @Test
    public void getBean() {
        BizService service = SpringUtils.getBean(BizService.class);
        service.hello();
    }

    @Test
    public void showAllBean() {
        SpringUtils.showAllBean();
    }

}