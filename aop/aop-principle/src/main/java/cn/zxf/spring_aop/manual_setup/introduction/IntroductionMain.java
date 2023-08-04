package cn.zxf.spring_aop.manual_setup.introduction;

import org.springframework.aop.framework.ProxyFactory;

/**
 * 参考：https://blog.csdn.net/zyhlwzy/article/details/70314157
 * <p>
 * Created by zengxf on 2020/6/5.
 */
public class IntroductionMain {
    public static void main(String[] args) {
        ProxyFactory proxyFactory = new ProxyFactory();
        GreetingImpl greetingImpl = new GreetingImpl();
        proxyFactory.setTarget(greetingImpl);
        proxyFactory.addAdvice(new GreetingIntroAdvice());
        Apology greeting = (Apology) proxyFactory.getProxy();
        greetingImpl.sayHello("Ron.Zheng");
        greeting.saySorry("Ron.Zheng");
    }
}
