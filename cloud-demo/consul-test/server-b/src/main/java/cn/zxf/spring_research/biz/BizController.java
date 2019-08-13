package cn.zxf.spring_research.biz;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( "/api/biz" )
public class BizController {

    @Autowired
    ServerAApi api;

    @Value( "${server.port}" )
    String     port;

    // http://localhost:9002/api/biz/hello
    @GetMapping( "hello" )
    public String hello() {
        String value = port + " - hello - " + System.currentTimeMillis() % 10000;
        System.out.println( "return: " + value );
        return value;
    }

    // http://localhost:9002/api/biz/get-user/002
    @GetMapping( "get-user/{id}" )
    public UserDto getUser( @PathVariable String id ) {
        UserDto dto = UserDto.of( id, "test-" + id, new Date() );
        System.out.println( "return: " + dto );
        return dto;
    }

    // http://localhost:9002/api/biz/test
    @GetMapping( "test" )
    public String test() {
        return "test - " + api.hello();
    }

}
