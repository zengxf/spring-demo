package test.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * <br/>
 * Created by ZXFeng on 2023/2/24.
 */
@FeignClient(
        name = "d-ser-1", path = "/api/biz",
        contextId = "dSer1BizFeign"
)
public interface DSer1BizFeign {

    @GetMapping("hello")
    String hello();

}
