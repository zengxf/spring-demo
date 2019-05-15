package cn.zxf.spring_research.biz;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;

@RestController
@RequestMapping( "/api/biz" )
public class BizController {

    @Value( "${test-test1:无}" )
    private String test1;

    @Value( "${ns1.name:无}" )
    private String ns1Name;

    // http://localhost:9001/api/biz/hello
    @GetMapping( "hello" )
    public String hello() {
        return String.format( "hello-[%s]-[%s]", test1, ns1Name );
    }

    // http://localhost:9001/api/biz/config
    @GetMapping( "config" )
    public Map<String, Object> config() {
        Config config = ConfigService.getAppConfig();
        Config ns1Config = ConfigService.getConfig( "ns1" );
        return Map.of( "def", config.getPropertyNames(), "ns1", ns1Config.getPropertyNames() );
    }

}
