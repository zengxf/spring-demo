package test;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * JUnit 5
 */
@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ExtendWith(SpringExtension.class)
public abstract class BaseSeataMainAppTest5 {

    @BeforeEach
    public void before() {
        log.info("\n *-*-*-* unit before *-*-*-* \n\n");
    }

    @AfterEach
    public void after() {
        log.info("\n\n *-*-*-* unit after  *-*-*-* ");
    }

}
