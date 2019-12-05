package test.biz;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;
import test.AbstractApplicationTest;

@DisplayName( "测试 JUnit5" )
@Slf4j
public class TestJUnit5 extends AbstractApplicationTest {

    @Tag( "测试" )
    @DisplayName( "测试 2" )
    @Test
    public void test1() {
        log.info( "test1" );
    }

    @Tag( "测试" )
    @DisplayName( "测试 1" )
    @Test
    public void test2() {
        log.info( "test2" );
    }

    @DisplayName( "测试 Set" )
    @Test
    public void testSet() {
        log.info( "testSet" );
    }

}
