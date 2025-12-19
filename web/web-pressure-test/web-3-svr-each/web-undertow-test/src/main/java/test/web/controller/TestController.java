package test.web.controller;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/test")
@Slf4j
public class TestController {

    @SneakyThrows
    @GetMapping("/find-sleep/{userId}/{sleepMs}")
    public Map<String, Object> findSleep(
            @PathVariable String userId,
            @PathVariable Integer sleepMs
    ) {
        Thread.sleep(sleepMs);
        Map<String, Object> map = new HashMap<>();
        map.put("user-id", userId);
        map.put("user-id-length", userId.length());
        return map;
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
