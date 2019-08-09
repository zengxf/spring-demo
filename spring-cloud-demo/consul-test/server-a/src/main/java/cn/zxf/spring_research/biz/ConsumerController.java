package cn.zxf.spring_research.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( "/api/consumer" )
public class ConsumerController {

    @Autowired
    private LoadBalancerClient loadBalancerClient;
    @Autowired
    private DiscoveryClient    discoveryClient;

    // http://localhost:9001/api/consumer/getServices
    @GetMapping( value = "/getServices" )
    public Object getServices() {
        return discoveryClient.getInstances( "server-b" );
    }

    // http://localhost:9001/api/consumer/chooseService
    // 轮询的选择同服务(来自不同的客户注册中心,IP不同)
    @GetMapping( value = "/chooseService" )
    public Object chooseService() {
        return loadBalancerClient.choose( "server-b" )
                .getUri()
                .toString();
    }

}
