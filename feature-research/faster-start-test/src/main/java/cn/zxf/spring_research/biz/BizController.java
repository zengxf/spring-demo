package cn.zxf.spring_research.biz;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping( "/api/biz" )
public class BizController {

    // http://localhost:9001/api/biz/hello
    @GetMapping( "hello" )
    public Map<String, Object> hello() {
        Map<String, Object> map = Map.of( "msg", "hello", "year", 2019 );
        log.info( "res: {}", map );
        return map;
    }

}
