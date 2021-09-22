package test.biz;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( "/api/biz" )
public class BizController {

    // http://localhost:9066/api/biz/hello
    @GetMapping( "hello" )
    public String hello() {
        return "hello";
    }

}
