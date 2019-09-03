package cn.zxf.spring_research.biz;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
// @Order( -10 )
@Component
public class MyGloablFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter( ServerWebExchange exchange, GatewayFilterChain chain ) {
        log.info( "进入全局过滤器" );
        return chain.filter( exchange );
    }

    @Override
    public int getOrder() {
        return -10;
    }

}
