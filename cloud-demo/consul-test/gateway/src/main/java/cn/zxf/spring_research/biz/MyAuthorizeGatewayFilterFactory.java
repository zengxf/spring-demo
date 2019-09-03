package cn.zxf.spring_research.biz;

import java.util.Arrays;
import java.util.List;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MyAuthorizeGatewayFilterFactory extends AbstractGatewayFilterFactory<MyAuthorizeGatewayFilterFactory.Config> {

    public MyAuthorizeGatewayFilterFactory() {
        super( Config.class );
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList( "enabled" );
    }

    @Override
    public GatewayFilter apply( Config config ) {
        return ( exchange, chain ) -> {
            log.info( "enabled: {}", config.enabled );
            ServerHttpRequest request = exchange.getRequest();
            log.info( "X1-Test: {}", request.getHeaders()
                    .getFirst( "X1-Test" ) );
            ServerHttpRequest.Builder builder = request.mutate();
            return chain.filter( exchange.mutate()
                    .request( builder.build() )
                    .build() );
        };
    }

    @Data
    public static class Config {
        private boolean enabled;
    }

}