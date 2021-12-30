package test.biz;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import test.AbstractApplicationTest5;

/**
 * <br/>
 * Created by ZXFeng on 2021/12/30.
 */
@DisplayName("异步类测试")
public class AsyncClassServiceTest extends AbstractApplicationTest5 {

    @Autowired
    AsyncClassService service;

    @Test
    @DisplayName("测试打印")
    public void testPrint() {
        service.print();
    }

    @Test
    @DisplayName("测试错误")
    public void testError() {
        service.error();
    }

}