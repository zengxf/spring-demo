package test.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * <br/>
 * Created by ZXFeng on 2023/2/24.
 */
@FeignClient(
        name = "my-ser-1", path = "/api/biz",
        contextId = "ser1BizFeign"
)
public interface Ser1BizFeign {

    @GetMapping("hello")
    String hello();

}
