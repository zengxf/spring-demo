package cn.zxf.spring_aop.manual_setup.advice3;

import org.springframework.aop.framework.ProxyFactory;

public class DynamicSpringProxy {

    public Object getProxy(Object target) {
        ProxyFactory factory = new ProxyFactory();
        // 让 Spring 走 CGLib 的动态代理，原理在 DefaultAopProxyFactory 里查看
        factory.setTarget(target);
//        factory.setInterfaces( IHello.class ); // 设置此会使用 Java 动态代理
        factory.addAdvice(new BeforeAdvice());
        factory.addAdvice(new SurroundAdvice());
        factory.addAdvice(new AfterAdvice());
        return factory.getProxy();
    }

}
