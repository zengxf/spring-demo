package test.webflux.controller;

import java.util.HashMap;
import java.util.Map;

import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Slf4j
@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping("/find-by-id")
    public Flux<Map<String, Object>> findById(@RequestParam String userId) {
        return Flux.just(userId)
                // .log()
                .flatMap(r -> Mono.just(r).subscribeOn(Schedulers.parallel()), 10)
                .flatMap(id -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("user-id", userId);
                    map.put("user-id-length", userId.length());
                    return Mono.just(map);
                });
    }

    @GetMapping("/find-sleep/{userId}/{sleepMs}")
    public Flux<Map<String, Object>> findSleep(
            @PathVariable String userId,
            @PathVariable Integer sleepMs
    ) {
        return Flux.just(userId)
                .flatMap(r -> Mono.just(r).subscribeOn(Schedulers.parallel()), 10)
                .flatMap(id -> {
                    try {
                        Thread.sleep(sleepMs);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    Map<String, Object> map = new HashMap<>();
                    map.put("user-id", userId);
                    map.put("user-id-length", userId.length());
                    return Mono.just(map);
                });
    }

    @GetMapping("/find-one/{id}")
    public Map<String, Object> findOne(@PathVariable String id) {
        Map<String, Object> map = Map.of(
                "id", id, "name",
                "峰-" + id,
                "status", 1,
                "date", System.currentTimeMillis()
        );
        log.info("find-map: {}", map);
        return map;
    }

}
