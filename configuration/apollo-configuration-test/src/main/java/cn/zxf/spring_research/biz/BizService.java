package cn.zxf.spring_research.biz;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class BizService {

    @ApolloConfig
    private Config config;

    @PostConstruct
    public void init() {
        log.info( "== BizService init! ==" );
        log.info( "config-property-names: {}", config.getPropertyNames() );
    }

    public void hello() {
        log.info( "== hello ==" );
    }

}
