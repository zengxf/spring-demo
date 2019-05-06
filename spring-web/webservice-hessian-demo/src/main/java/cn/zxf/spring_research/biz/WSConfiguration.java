package cn.zxf.spring_research.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianServiceExporter;

@Configuration
public class WSConfiguration {

    @Autowired
    private HelloService helloService;

    @Bean( name = "/HelloService" )
    public HessianServiceExporter accountService() {
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService( helloService );
        exporter.setServiceInterface( HelloService.class );
        return exporter;
    }

}
