package cn_zxf_test.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/biz")
public class BizController {

    @Autowired
    private BizService service;

    // http://localhost:9001/api/biz/hello
    @GetMapping("hello")
    public String hello() {
        service.hello();
        return "hello";
    }

}
