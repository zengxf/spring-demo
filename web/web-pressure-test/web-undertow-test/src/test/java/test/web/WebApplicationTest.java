package test.web;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * <br/>
 * Created by ZXFeng on 2023/3/4.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ExtendWith(SpringExtension.class)
@Slf4j
class WebApplicationTest {

    @Value("${server.port}")
    private Integer port;

    @Test
    @DisplayName("测试")
    public void test() {
        log.info("OK! port: [{}]", port);
    }

}