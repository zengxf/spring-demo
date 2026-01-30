package com.example.mcp.tools;

import lombok.extern.slf4j.Slf4j;
import org.springaicommunity.mcp.annotation.McpResource;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * <p/>
 * Created by ZXFeng on 2026/1/30
 */
@Component
@Slf4j
public class MyRes {

    // @McpResource(name = "xxx", uri = "file://config/settings.json")
    // public Mono<String> myResource() {
    //     log.info("================> OK");
    //     return Mono.just("OK ==");
    // }

    @McpResource(name = "xxx", uri = "file://config/settings.json")
    public String myResource() {
        log.info("================> OK");
        return "OK ==";
    }

}
