package test.biz;

import java.util.Map;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class BizService {

    @PostConstruct
    public void init() {
        log.info( "== BizService init! ==" );
    }

    public void hello() {
        log.info( "== hello ==" );
    }

    @Cacheable( "key-findInfo" )
    public Map<String, Object> findInfo() {
        Map<String, Object> map = Map.of( "test", "s-" + new Random().nextInt( 10 ) );
        log.info( "-- map: {}", map );
        return map;
    }

    @CachePut( "key-findInfo11" )
    public Map<String, Object> findInfo11() {
        Map<String, Object> map = Map.of( "test", "s-" + new Random().nextInt( 10 ) );
        log.info( "-- map: {}", map );
        return map;
    }

}
