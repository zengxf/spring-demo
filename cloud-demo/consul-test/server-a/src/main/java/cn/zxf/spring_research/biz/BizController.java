package cn.zxf.spring_research.biz;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( "/api/biz" )
public class BizController {

    @Autowired
    ServerBApi api;

    // http://localhost:9001/api/biz/hello
    @GetMapping( "hello" )
    public String hello( HttpServletRequest request ) {
        String value = "server-a = hello - " + System.currentTimeMillis() % 10000;
        System.out.println( "return: " + value );
        System.out.println( "headler: " + request.getHeader( "X-Test" ) );
        return value;
    }

    // http://localhost:9001/api/biz/test
    @GetMapping( "test" )
    public String test() {
        return "test - " + api.hello();
    }

    // http://localhost:9001/api/biz/test-post
    @GetMapping( "test-post" )
    public String testPost() {
        return "test - " + api.helloPost();
    }

    // http://localhost:9001/api/biz/test-user
    @GetMapping( "test-user" )
    public UserDto testUser() {
        String id = "obj-" + System.currentTimeMillis() % 10000;
        return api.getUser( id );
    }

    // http://localhost:9001/api/biz/test-user-map
    @GetMapping( "test-user-map" )
    public Map<String, Object> testUserMap() {
        String id = "map-" + System.currentTimeMillis() % 10000;
        return api.getUserMap( id );
    }

}
