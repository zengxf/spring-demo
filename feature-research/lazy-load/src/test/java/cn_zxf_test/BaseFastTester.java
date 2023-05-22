package cn_zxf_test;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@ContextConfiguration( // 这种方式可以不运行 ApplicationRunner
        classes = UnitLazyApp.class,
        initializers = ConfigDataApplicationContextInitializer.class
)
@RunWith(SpringRunner.class) // JUnit-4 测试
public abstract class BaseFastTester {

    static ThreadLocal<Long> startUnitTs = new ThreadLocal<>();
    static ThreadLocal<Long> startAppTs = new ThreadLocal<>();


    @BeforeClass
    public static void beforeClass() {
        log.info("\n------- App start -------\n\n\n");
        startAppTs.set(System.currentTimeMillis());
    }

    @AfterClass
    public static void afterClass() {
        long us = System.currentTimeMillis() - startAppTs.get();
        log.info("\n\n\n------- App end -------");
        log.info("App 用时：[{}]", fmtMs(us));
    }

    @Before
    public void before() {
        log.info("\n------- Unit start ------\n\n\n");
        startUnitTs.set(System.currentTimeMillis());
    }

    @After
    public void after() {
        long us = System.currentTimeMillis() - startUnitTs.get();
        log.info("\n\n\n------- Unit end ------");
        log.info("Unit 用时：[{}]", fmtMs(us));
    }

    static String fmtMs(long ms) {
        long mm = ms / 60_000L;
        long ss = ms / 1000L % 60L;
        long m = ms % 1000L;
        return String.format("%s m %s s %s ms", mm, ss, m);
    }

}
