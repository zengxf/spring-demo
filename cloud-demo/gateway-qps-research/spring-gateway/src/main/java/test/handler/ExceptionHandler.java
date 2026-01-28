package test.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

/**
 * 异常处理
 * <p/>
 * Created by ZXFeng on 2026/1/28
 */
@Order(-1)
@RequiredArgsConstructor
@Slf4j
public class ExceptionHandler implements ErrorWebExceptionHandler {

    private final ObjectMapper objectMapper;

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        log.info("==== 异常处理 ====");
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        if (response.isCommitted()) {
            return Mono.error(ex);
        }

        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        if (ex instanceof ResponseStatusException) {
            response.setStatusCode(((ResponseStatusException) ex).getStatusCode());
        }

        return response.writeWith(Mono.fromSupplier(() -> {
            DataBufferFactory bufferFactory = response.bufferFactory();
            try {
                int status = 500;
                if (response.getStatusCode() != null) {
                    status = response.getStatusCode().value();
                }
                Map<String, Object> result = response(status, this.buildMessage(request, ex));
                return bufferFactory.wrap(objectMapper.writeValueAsBytes(result));
            } catch (JsonProcessingException e) {
                return bufferFactory.wrap(new byte[0]);
            }
        }));
    }


    /**
     * 构建异常信息
     */
    private String buildMessage(ServerHttpRequest request, Throwable ex) {
        StringBuilder message = new StringBuilder("限流处理 [");
        message.append(request.getMethod());
        message.append(" ");
        message.append(request.getURI());
        message.append("]");
        if (ex != null) {
            message.append(": ");
            message.append(ex.getMessage());
        }
        return message.toString();
    }

    private static Map<String, Object> response(int status, String message) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", status);
        map.put("msg", message);
        map.put("data", null);
        return map;
    }

}
