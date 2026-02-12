// package com.alibaba.cloud.ai.mcp.client;
//
// import okhttp3.Interceptor;
// import okhttp3.Request;
// import okio.Buffer;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
//
// /**
//  * <p/>
//  * Created by ZXFeng on 2026/2/11
//  */
// @Configuration
// public class DashScopeOkHttpConfig {
//     private static final Logger log = LoggerFactory.getLogger(DashScopeOkHttpConfig.class);
//
//     @Bean
//     public Interceptor okHttpLoggingInterceptor() {
//         return chain -> {
//             Request request = chain.request();
//
//             // 拦截发往阿里 DashScope 的请求
//             if (request.url().toString().contains("dashscope")) {
//                 Buffer buffer = new Buffer();
//                 if (request.body() != null) {
//                     request.body().writeTo(buffer);
//                     String body = buffer.readUtf8();
//                     log.info(">>>> DashScope Request JSON: {}", body);
//                 }
//             }
//
//             return chain.proceed(request);
//         };
//     }
// }
