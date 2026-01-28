package test.filter;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import test.exception.FlowException;

/**
 * 流量限制过滤器
 * <p/>
 * Created by ZXFeng on 2026/1/27
 */
@Component
@Slf4j
@Order(-100) // 🔥 必须在 NettyWriteResponseFilter(-1) 之前执行，否则来不及包装响应
public class ResponseSizeLimitFilter implements GlobalFilter {

    private static final int MAX_SIZE_BYTES = 2 * 1024; // 2KB 限制

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("==== 进入 ====");

        ServerHttpResponse originalResponse = exchange.getResponse();
        ServerHttpResponseDecorator decoratedResponse = new ServerHttpResponseDecorator(originalResponse) {
            @Override
            public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
                // 1. 将 body 流聚合成一个完整的 DataBuffer
                return DataBufferUtils.join(body)
                        .flatMap(dataBuffer -> {
                            int totalSize = dataBuffer.readableByteCount();

                            // 2. 判断总长度
                            if (totalSize > MAX_SIZE_BYTES) {
                                // 必须手动释放内存，防止内存泄漏
                                DataBufferUtils.release(dataBuffer);
                                // 抛出异常，此时 Response Header 尚未发送，可以被 ExceptionHandler 捕获
                                return Mono.error(new FlowException(totalSize, MAX_SIZE_BYTES));
                            }

                            // 3. 如果没超限，将聚合后的 buffer 重新包装成 Flux 发送
                            return super.writeWith(Mono.just(dataBuffer));
                        })
                        // 4. 处理 body 为空的情况
                        .switchIfEmpty(super.writeWith(Mono.empty()));
            }
        };

        // 用包装后的响应替换掉 exchange 的响应对象
        return chain.filter(exchange.mutate().response(decoratedResponse).build());
    }

}
