package cn_zxf_test;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;

@ActiveProfiles("s01")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@RunWith(SpringRunner.class)
@Slf4j
public abstract class BaseTester {

    @Before
    public void before() {
        log.info("\n *-*-*-* unit before *-*-*-* \n\n");
    }

    @After
    public void after() {
        log.info("\n\n *-*-*-* unit after  *-*-*-* ");
    }

}
