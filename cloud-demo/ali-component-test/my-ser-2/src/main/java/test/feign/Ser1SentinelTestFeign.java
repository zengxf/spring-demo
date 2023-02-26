package test.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * <br/>
 * Created by ZXFeng on 2023/2/26.
 */
@FeignClient(
        name = "my-ser-1", path = "/api/biz",
        contextId = "ser1SentinelTestFeign",
        fallback = Ser1SentinelTestFeignFallback.class
)
public interface Ser1SentinelTestFeign {

    @GetMapping("sentinel-test")
    String sentinelTest();

    @GetMapping("get-array")
    List<String> getArray();

}
