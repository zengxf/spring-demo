package test.biz;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.feign.Ser1BizFeign;

/**
 * <br/>
 * Created by ZXFeng on 2024/1/3.
 */
@Slf4j
@RestController
@RequestMapping("/api/biz")
public class BizController {

    @Autowired
    private Ser1BizFeign ser1BizFeign;

    // http://localhost:6892/api/biz/hello
    @GetMapping("hello")
    public String hello() {
        log.info("hello --------------");
        return "hello";
    }

    // http://localhost:6892/api/biz/testFeign
    @GetMapping("testFeign")
    public String testFeign() {
        String res = ser1BizFeign.hello();
        log.info("testFeign -------------- " + res);
        return "testFeign -- " + res;
    }


}
