package cn.zxf.sentinel.cluster.gateway.filter;

import cn.zxf.sentinel.cluster.config.RuleSetupUtils;
import cn.zxf.sentinel.cluster.config.RuleType;
import cn.zxf.sentinel.cluster.gateway.config.RuleLoadHelper;
import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * Sentinel Gateway QPS 流控过滤器
 * 使用 Sentinel 集群流控对网关请求进行限流
 */
@Slf4j
@Component
public class SentinelQpsFilter implements GlobalFilter, Ordered {

    @Value("${my.sign:xx}")
    private String sign;

    @Resource
    private RuleLoadHelper ruleLoadHelper;


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String resourceName = ResourceNameUtils.getResourceName(ruleLoadHelper, exchange, RuleType.QPS);

        if (RuleSetupUtils.isNotEnabled(resourceName)) { // 未开启限流
            return chain.filter(exchange);
        }

        Entry entry = null;
        try {
            // 使用 Sentinel 进行流控检查
            entry = SphU.entry(resourceName);
            log.debug("请求通过流控检查: {}", resourceName);
            return chain.filter(exchange);
        } catch (BlockException ex) {
            // 被流控拦截
            String path = exchange.getRequest().getPath().value();
            log.warn("请求被流控拦截: {}, 资源: {}", path, resourceName);
            return handleBlockException(exchange, ex);
        } finally {
            if (entry != null) {
                entry.exit();
            }
        }
    }

    /**
     * 处理流控异常
     */
    private Mono<Void> handleBlockException(ServerWebExchange exchange, BlockException ex) {
        exchange.getResponse().setStatusCode(HttpStatus.TOO_MANY_REQUESTS);
        exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);

        String errorMsg = String.format(
                "{\"code\":429,\"message\":\"请求过于频繁,请稍后再试\",\"type\":\"%s\",\"sign\":\"%s\"}",
                ex.getClass().getSimpleName(), sign
        );

        return exchange.getResponse().writeWith(
                Mono.just(exchange.getResponse().bufferFactory().wrap(errorMsg.getBytes()))
        );
    }

    @Override
    public int getOrder() {
        return -200;
    }

}
