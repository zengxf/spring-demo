package test.filter;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import test.exception.QpsException;

/**
 * QPS 限制过滤器
 * <p/>
 * Created by ZXFeng on 2026/1/28
 */
@Component
@Slf4j
@Order(-200)
public class QpsLimitFilter implements GlobalFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest().mutate().build();
        String rawPath = request.getURI().getRawPath();
        String[] array = StringUtils.tokenizeToStringArray(rawPath, "/");

        String svrName = array[0];
        log.info("==== 提取 svrName: [{}]", svrName);

        try (Entry entry = SphU.entry(svrName)) {
            log.info("entry ==> {}", entry.getResourceWrapper());
            return chain.filter(exchange);
        } catch (BlockException ex) {
            // 3. 如果被限流或降级，会抛出 BlockException
            log.error("限流", ex);
            throw new QpsException(4, 3);
        }

    }

}
