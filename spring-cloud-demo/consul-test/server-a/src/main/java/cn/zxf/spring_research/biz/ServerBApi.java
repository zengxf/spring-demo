package cn.zxf.spring_research.biz;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Component
@FeignClient( "server-b" )
public interface ServerBApi {

    @GetMapping( "/api/biz/hello" )
    public String hello();

    @PostMapping( "/api/biz/hello" )
    public String helloPost();

    @GetMapping( "/api/biz/get-user/{id}" )
    public UserDto getUser( @PathVariable( "id" ) String id );

    @GetMapping( "/api/biz/get-user/{id}" )
    public Map<String, Object> getUserMap( @PathVariable( "id" ) String id );

}
