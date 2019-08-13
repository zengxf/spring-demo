package cn.zxf.spring_research.biz;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;
import com.ctrip.framework.apollo.core.enums.ConfigFileFormat;

@RestController
@RequestMapping( "/api/biz" )
public class BizController {

    @Value( "${test-test1:无}" )
    private String test1;
    @Value( "${ns1.name:无}" )
    private String ns1Name;
    @Value( "${dev.name:无}" )
    private String devName;

    // http://localhost:9001/api/biz/hello
    @GetMapping( "hello" )
    public String hello() {
        return String.format( "hello-[%s]-[%s]-[%s]", test1, ns1Name, devName );
    }

    // http://localhost:9001/api/biz/config
    @GetMapping( "config" )
    public Map<String, Object> config() {
        Map<String, Object> cM = toMap( ConfigService.getAppConfig() );
        Map<String, Object> ns1M = toMap( ConfigService.getConfig( "ns1" ) );
        String ns2Content = ConfigService.getConfigFile( "ns2", ConfigFileFormat.YAML )
                .getContent();
        return Map.of( "def", cM, "ns1", ns1M, "ns2Content", ns2Content );
    }

    Map<String, Object> toMap( Config config ) {
        Map<String, Object> cM = new HashMap<>();
        config.getPropertyNames()
                .forEach( key -> cM.put( key, config.getProperty( key, "无" ) ) );
        return cM;
    }

}
