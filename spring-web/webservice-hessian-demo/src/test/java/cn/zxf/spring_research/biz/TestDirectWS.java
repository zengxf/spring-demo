package cn.zxf.spring_research.biz;

import java.net.MalformedURLException;

import org.junit.Test;

import com.caucho.hessian.client.HessianProxyFactory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestDirectWS {

    @Test
    public void test() throws MalformedURLException {
        HessianProxyFactory factory = new HessianProxyFactory();
        String url = "http://localhost:9011/HelloService";
        HelloService service = (HelloService) factory.create( HelloService.class, url );
        log.info( "test-say: {}", service.sayHello() );
        log.info( "test-foo: {}", service.foo().getName() );
    }

}
