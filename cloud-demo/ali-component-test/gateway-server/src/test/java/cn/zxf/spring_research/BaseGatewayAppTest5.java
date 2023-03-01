package cn.zxf.spring_research;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Slf4j
// @RunWith( SpringRunner.class )
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@ExtendWith(SpringExtension.class)
public abstract class BaseGatewayAppTest5 {

    @BeforeEach
    public void before() {
        log.info("\n *-*-*-* unit before *-*-*-* \n\n");
    }

    @AfterEach
    public void after() {
        log.info("\n\n *-*-*-* unit after  *-*-*-* ");
    }

}
