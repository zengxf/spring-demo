package cn.zxf.spring_research.biz;

import java.util.Objects;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping( "/api/biz" )
public class BizController {

    @Autowired
    private BizService        service;

    @Autowired
    private HazelcastInstance hz;

    // http://localhost:9001/api/biz/hello
    @GetMapping( "hello" )
    public String hello() {
        return "hello";
    }

    // http://localhost:9011/api/biz/greet
    @GetMapping( "/greet" )
    public Object greet() {
        Object value = Hazelcast.getHazelcastInstanceByName( "hazelcast-instance" )
                .getMap( "instruments" )
                .get( "hello" );
        if ( Objects.isNull( value ) ) {
            Hazelcast.getHazelcastInstanceByName( "hazelcast-instance" )
                    .getMap( "instruments" )
                    .put( "hello", "world!" );

        }
        log.info( "从分布式缓存获取到 key=hello,value={}", value );
        return value == null ? "null" : value;
    }

    // http://localhost:9011/api/biz/greet2
    @GetMapping( "/greet2" )
    public Object greet2() {
        Object value = hz.getMap( "instruments" )
                .get( "hello" );
        if ( Objects.isNull( value ) ) {
            Hazelcast.getHazelcastInstanceByName( "hazelcast-instance" )
                    .getMap( "instruments" )
                    .put( "hello", "world!" );

        }
        log.info( "从分布式缓存获取到 key=hello,value={}", value );
        return value == null ? "null" : value;
    }

    // http://localhost:9011/api/biz/cache
    @GetMapping( "/cache" )
    public Object cache() {
        String value = service.greet( "hello-2" );
        log.info( "从分布式缓存获取到 key=hello-2,value={}", value );
        return value;
    }

    // http://localhost:9011/api/biz/session
    @GetMapping( "/session" )
    public Object session( HttpSession session ) {
        String sessionId = session.getId();
        log.info( "当前请求的 sessionId={}", sessionId );
        return sessionId;
    }

}
