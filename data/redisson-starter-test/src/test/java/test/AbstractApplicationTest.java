package test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtendWith( SpringExtension.class )
@SpringBootTest( webEnvironment = WebEnvironment.NONE )
public abstract class AbstractApplicationTest {

    @BeforeEach
    public void before() {
        log.info( "\n *-*-*-* unit before *-*-*-* \n\n" );
    }

    @AfterEach
    public void after() {
        log.info( "\n\n *-*-*-* unit after  *-*-*-* " );
    }

}
