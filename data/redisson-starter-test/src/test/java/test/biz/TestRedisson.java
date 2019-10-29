package test.biz;

import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RSet;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;
import test.AbstractApplicationTest;

@DisplayName( "测试 Redisson" )
@Slf4j
public class TestRedisson extends AbstractApplicationTest {

    @Autowired
    RedissonClient      redisson;

    static final String KEY = "zxf-test";

    @Tag( "测试布隆过滤器" )
    @DisplayName( "测试布隆 Add" )
    @Test
    public void testBloomAdd() {
        RBloomFilter<String> bf = redisson.getBloomFilter( KEY );
        bf.tryInit( 1000L, 0.001D );
        for ( int i = 0; i < 2000; i++ ) {
            bf.add( "zxf-test-" + i );
        }
        log.info( "size: {}", bf.getSize() );
    }

    @Tag( "测试布隆过滤器" )
    @DisplayName( "测试布隆 Contains" )
    @Test
    public void testBloomContains() {
        RBloomFilter<String> bf = redisson.getBloomFilter( KEY );
        log.info( "contains 1: {}", bf.contains( "zxf-test-1" ) );
        log.info( "contains 1000: {}", bf.contains( "zxf-test-1000" ) );
        log.info( "contains 1999: {}", bf.contains( "zxf-test-1999" ) );
        log.info( "contains 2000: {}", bf.contains( "zxf-test-2000" ) );
        log.info( "contains 3000: {}", bf.contains( "zxf-test-3000" ) );
    }

    @DisplayName( "测试 Set" )
    @Test
    public void testSet() {
        RSet<String> set = redisson.getSet( "zxf-set" );
        set.add( "test-1" );
        set.add( "test-2" );
        set.add( "test-3" );
        String[] arr = set.toArray( new String[] {} );
        log.info( "arr: {}", Arrays.toString( arr ) );
    }

}
