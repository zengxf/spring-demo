package cn.zxf.spring_research.biz;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service( "helloService" )
public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello() {
        log.info( "in say hello()" );
        return "Hello World";
    }

    @Override
    public Foo foo() {
        log.info( "in say foo()" );
        return new Foo( "foo" );
    }
}
