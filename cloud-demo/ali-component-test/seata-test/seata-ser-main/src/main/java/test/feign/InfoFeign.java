package test.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <br/>
 * Created by ZXFeng on 2023/2/24.
 */
@FeignClient(
        name = "seata-info", path = "/api/biz",
        contextId = "seataInfoFeign"
)
public interface InfoFeign {

    @GetMapping("operate")
    String operate(
            @RequestParam Integer sign,
            @RequestParam Integer uid,
            @RequestParam Integer money
    );

}
