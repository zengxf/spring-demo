package cn.zxf.spring_research.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.zxf.starter.ExampleService;

@RestController
@RequestMapping( "/api/biz" )
public class BizController {

    @Autowired
    private ExampleService exampleService;

    // http://localhost:9001/api/biz/input?word=zxf
    @GetMapping( "input" )
    public String input( @RequestParam String word ) {
        return exampleService.wrap( word );
    }

    // http://localhost:9001/api/biz/error?word=zxf
    @GetMapping( "error" )
    public String error( @RequestParam String word ) {
        if ( "zxf".equals( word ) ) {
            return "OK";
        }
        throw new RuntimeException( "test error" );
    }

}
