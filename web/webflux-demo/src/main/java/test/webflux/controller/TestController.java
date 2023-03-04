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

    @GetMapping("/create")
    public Flux<Map<String, Object>> create(@RequestParam String userId) {
        return Flux.just(userId)
                .log()
                .flatMap(r -> Mono.just(r).subscribeOn(Schedulers.parallel()), 10)
                .flatMap(id -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("user-id", userId);
                    map.put("user-id-length", userId.length());
                    return Mono.just(map);
                });
    }

    @SneakyThrows
    @GetMapping("/create-sleep/{userId}/{sleepMs}")
    public Flux<Map<String, Object>> createSleep(
            @PathVariable String userId,
            @PathVariable Integer sleepMs
    ) {
        Thread.sleep(sleepMs);
        return Flux.just(userId)
                .log()
                .flatMap(r -> Mono.just(r).subscribeOn(Schedulers.parallel()), 10)
                .flatMap(id -> {
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
                "峰-" + id, "status",
                1, "date", System.currentTimeMillis()
        );
        log.info("find-map: {}", map);
        return map;
    }

    @PostMapping("/update-one")
    public Map<String, Object> updateOne(@RequestBody Map<String, Object> body) {
        log.info("update-body: {}", body);
        Map<String, Object> map = Map.of(
                "msg", "ok",
                "body", body,
                "code", 1,
                "date", System.currentTimeMillis()
        );
        log.info("update-map: {}", map);
        return map;
    }

    @PutMapping("/put-one")
    public Map<String, Object> putOne(@RequestBody Map<String, Object> body) {
        log.info("put-body: {}", body);
        Map<String, Object> map = Map.of(
                "msg", "ok",
                "body", body,
                "code", 2,
                "date", System.currentTimeMillis()
        );
        log.info("put-map: {}", map);
        return map;
    }

    @PatchMapping("/patch-one")
    public Map<String, Object> patchOne(@RequestBody Map<String, Object> body) {
        log.info("patch-body: {}", body);
        Map<String, Object> map = Map.of(
                "msg", "ok",
                "body", body,
                "code", 3,
                "date", System.currentTimeMillis()
        );
        log.info("patch-map: {}", map);
        return map;
    }

    @DeleteMapping("/delete-one/{id}")
    public Map<String, Object> deleteOne(@PathVariable String id) {
        Map<String, Object> map = Map.of(
                "id", id,
                "name", "峰-" + id,
                "delete-status", 1,
                "date", System.currentTimeMillis()
        );
        log.info("delete-map: {}", map);
        return map;
    }

}
