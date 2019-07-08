package cn.zxf.spring_research.biz;

import javax.annotation.PostConstruct;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CacheConfig( cacheNames = "instruments-xx" )
@Component
public class BizService {

    @PostConstruct
    public void init() {
        log.info( "== BizService init! ==" );
    }

    public void hello() {
        log.info( "== hello ==" );
    }

    @Cacheable( key = "#key" )
    public String greet( String key ) {
        log.info( "缓存内没有取到 key={}", key );
        return "world-2！";
    }

}
