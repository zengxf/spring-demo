package cn.zxf.sentinel.cluster.gateway.filter;

import cn.zxf.sentinel.cluster.config.RuleSetupUtils;
import cn.zxf.sentinel.cluster.config.RuleType;
import cn.zxf.sentinel.cluster.gateway.config.RuleLoadHelper;
import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * Sentinel Gateway 响应流量限制过滤器
 * <p/>
 * Created by ZXFeng on 2026/1/27
 */
@Component
@Slf4j
public class SentinelResSizeFilter implements GlobalFilter, Ordered {

    @Resource
    private RuleLoadHelper ruleLoadHelper;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String resourceName = ResourceNameUtils.getResourceName(ruleLoadHelper, exchange, RuleType.TRAFFIC);

        if (RuleSetupUtils.isNotEnabled(resourceName)) { // 未开启限流
            return chain.filter(exchange);
        }

        ServerHttpResponse originalResponse = exchange.getResponse();
        ServerHttpResponseDecorator decoratedResponse = new ServerHttpResponseDecorator(originalResponse) {
            @Override
            public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
                return DataBufferUtils.join(body)
                        .flatMap(dataBuffer -> {
                            int totalSize = dataBuffer.readableByteCount();
                            Entry entry = null;
                            try {
                                // 使用 Sentinel 进行流控检查
                                entry = SphU.entry(resourceName, totalSize);
                                log.debug("响应通过流控检查: {}", resourceName);
                                return super.writeWith(Mono.just(dataBuffer));
                            } catch (BlockException ex) {
                                // 被流控拦截
                                DataBufferUtils.release(dataBuffer);
                                String path = exchange.getRequest().getPath().value();
                                log.warn("响应被流控拦截: {}, 资源: {}", path, resourceName);

                                ServerHttpResponse response = getDelegate();
                                response.setStatusCode(HttpStatus.valueOf(419));
                                response.getHeaders().setContentType(MediaType.APPLICATION_JSON);

                                String errorMsg = "{\"code\":419, \"msg\":\"响应被限流！！！\"}";
                                DataBuffer buffer = response.bufferFactory().wrap(errorMsg.getBytes());
                                return response.writeWith(Mono.just(buffer));
                            } finally {
                                if (entry != null) {
                                    entry.exit();
                                }
                            }
                        })
                        .switchIfEmpty(super.writeWith(Mono.empty()));
            }
        };

        return chain.filter(exchange.mutate().response(decoratedResponse).build());
    }

    @Override
    public int getOrder() {
        return -210;
    }

}
