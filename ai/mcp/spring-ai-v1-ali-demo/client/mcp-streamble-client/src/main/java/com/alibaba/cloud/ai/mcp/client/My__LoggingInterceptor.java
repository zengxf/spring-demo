package com.alibaba.cloud.ai.mcp.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * <p/>
 * Created by ZXFeng on 2026/2/11
 */
public class My__LoggingInterceptor implements ClientHttpRequestInterceptor {
    private static final Logger log = LoggerFactory.getLogger(My__LoggingInterceptor.class);

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        // 1. 记录请求 (已有的逻辑)

        // 打印请求地址和请求体（这里就能看到组装好的 tools 字段）
        log.info("================ [DashScope Request API] ================");
        log.info("URI    : {}", request.getURI());
        log.info("Method : {}", request.getMethod());
        log.info("Body   : {}", new String(body, StandardCharsets.UTF_8));
        log.info("==========================================================");

        // 2. 执行请求获取响应
        ClientHttpResponse response = execution.execute(request, body);

        // 3. 记录响应 (注意：这里需要包装工厂支持流重用)
        String responseBody = StreamUtils.copyToString(response.getBody(), StandardCharsets.UTF_8);

        log.info("================ [DashScope API Response] ================");
        log.info("Response: {}", responseBody);
        log.info("==========================================================");

        return response;
    }
}
