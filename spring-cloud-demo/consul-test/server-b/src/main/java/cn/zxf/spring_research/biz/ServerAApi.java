package cn.zxf.spring_research.biz;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
@FeignClient( "server-a" )
public interface ServerAApi {

    @GetMapping( "/api/biz/hello" )
    public String hello();

}
