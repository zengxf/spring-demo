package cn.zxf.gateway.sentinel.filter;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.node.ClusterNode;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.clusterbuilder.ClusterBuilderSlot;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * Sentinel Gateway 流控过滤器
 * 使用 Sentinel 集群流控对网关请求进行限流
 */
@Slf4j
@Component
public class SentinelGatewayFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getPath().value();
        String resourceName = getResourceName(exchange);

        Entry entry = null;
        try {
            // 使用 Sentinel 进行流控检查
            entry = SphU.entry(resourceName);
            log.info("请求通过流控检查: {}", resourceName);

            // 获取 QPS 数据
            ClusterNode node = ClusterBuilderSlot.getClusterNode(resourceName);
            if (node != null) {
                double passQps = node.passQps();  // 通过的请求 QPS
                double blockQps = node.blockQps(); // 被限流的请求 QPS
                double totalQps = passQps + blockQps;
                log.info("{} 通过QPS: {}", resourceName, passQps);
                log.info("{} 限流QPS: {}", resourceName, blockQps);
                log.info("{} 总QPS: {}", resourceName, totalQps);
            } else {
                log.info("{} 未找到资源节点，可能还未触发埋点", resourceName);
            }

            return chain.filter(exchange);

        } catch (BlockException ex) {
            // 被流控拦截
            log.warn("请求被流控拦截: {}, 资源: {}", path, resourceName);
            return handleBlockException(exchange, ex);

        } finally {
            if (entry != null) {
                entry.exit();
            }
        }
    }

    /**
     * 获取资源名称
     * 使用服务名作为资源名,实现对上游服务的流控
     */
    private String getResourceName(ServerWebExchange exchange) {
        String path = exchange.getRequest().getPath().value();

        // 从路径中提取服务名 (格式: /service-name/...)
        if (path.startsWith("/")) {
            String[] parts = path.substring(1).split("/");
            if (parts.length > 0) {
                return parts[0]; // 返回服务名作为资源名
            }
        }

        return "default";
    }

    /**
     * 处理流控异常
     */
    private Mono<Void> handleBlockException(ServerWebExchange exchange, BlockException ex) {
        exchange.getResponse().setStatusCode(HttpStatus.TOO_MANY_REQUESTS);
        exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);

        String errorMsg = String.format("{\"code\":429,\"message\":\"请求过于频繁,请稍后再试\",\"type\":\"%s\"}",
                ex.getClass().getSimpleName());

        return exchange.getResponse().writeWith(
                Mono.just(exchange.getResponse().bufferFactory().wrap(errorMsg.getBytes()))
        );
    }

    @Override
    public int getOrder() {
        return -100; // 优先级较高,在其他过滤器之前执行
    }
}
