package cn.zxf.spring_research.biz;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class BizService {

    @Value( "${test.value}" )
    String testValue;

    @PostConstruct
    public void init() {
        log.info( "== BizService init! ==" );
        log.info( "== test.value == {}", testValue );
    }

    public String testValue() {
        return this.testValue;
    }

    public void hello() {
        log.info( "== hello ==" );
    }

}
