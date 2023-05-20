package cn_zxf_test.units;

import cn_zxf_test.BaseNotLazyTester5;
import cn_zxf_test.biz.BizService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class SpringUtilsNotLazyTest extends BaseNotLazyTester5 {

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