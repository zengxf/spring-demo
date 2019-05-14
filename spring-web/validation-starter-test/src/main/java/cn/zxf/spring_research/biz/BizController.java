package cn.zxf.spring_research.biz;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping( "/api/biz" )
public class BizController {

    // http://localhost:9001/api/biz/hello
    @GetMapping( "hello" )
    public String hello() {
        return "hello";
    }

    // http://localhost:9001/api/biz/create1
    @PostMapping( "create1" )
    public String create( @Valid @RequestBody BizCreateDto dto, BindingResult res ) {
        log.info( "create-dto: {}", dto );
        if ( res.hasErrors() ) {
            res.getAllErrors()
                    .forEach( error -> log.info( "error: {}", error.getDefaultMessage() ) );
            return "error";
        }
        return "OK";
    }

    // http://localhost:9001/api/biz/create2
    @PostMapping( "create2" )
    public String create2( @Valid @RequestBody BizCreateDto dto ) {
        log.info( "create-dto: {}", dto );
        return "OK";
    }

}
