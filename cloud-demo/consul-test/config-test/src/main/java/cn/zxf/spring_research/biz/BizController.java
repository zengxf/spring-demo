package cn.zxf.spring_research.biz;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( "/api/biz" )
public class BizController {

    @Autowired
    ServerAApi    api;
    @Autowired
    BizService    service;
    @Autowired
    UserConfig    config;
    @Autowired
    MongoTemplate mongo;

    // http://localhost:9006/api/biz/save
    @GetMapping( "save" )
    public String save() {
        int r = new Random().nextInt( 100 );
        User user = User.builder()
                .name( "test-" + r )
                .age( r )
                .build();
        mongo.save( user );
        return "## user = " + user;
    }

    // http://localhost:9006/api/biz/all
    @GetMapping( "all" )
    public List<User> all() {
        return mongo.findAll( User.class );
    }

    // http://localhost:9006/api/biz/hello
    @GetMapping( "hello" )
    public String hello() {
        return "## biz.value = " + service.testValue() + " ## config = " + config;
    }

    // http://localhost:9006/api/biz/test
    @GetMapping( "test" )
    public String test() {
        return "test - " + api.hello();
    }

}
