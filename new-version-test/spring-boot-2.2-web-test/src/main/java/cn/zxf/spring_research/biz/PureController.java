package cn.zxf.spring_research.biz;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PureController {

    // http://localhost:9001/api/pure/hello
    @GetMapping( "/api/pure/hello" )
    public String hello() {
        return "hello";
    }

}
