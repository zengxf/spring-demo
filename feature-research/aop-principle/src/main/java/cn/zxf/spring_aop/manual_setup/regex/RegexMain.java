package cn.zxf.spring_aop.manual_setup.regex;

import cn.zxf.spring_aop.manual_setup.advice3.SurroundAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.RegexpMethodPointcutAdvisor;

/**
 * 参考：https://blog.csdn.net/zyhlwzy/article/details/70314157
 * <p>
 * Created by zengxf on 2020/6/5.
 */
public class RegexMain {

    public static void main(String[] args) {
        // 获得并设置切面
        RegexpMethodPointcutAdvisor advisor = new RegexpMethodPointcutAdvisor();
        advisor.setAdvice(new SurroundAdvice());
        advisor.setPattern("cn.zxf.spring_aop.manual_setup.regex.GreetingImpl.good.*");

        // 配置代理
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(new GreetingImpl());
        proxyFactory.addAdvisor(advisor);

        // 获取代理
        Greeting greeting = (Greeting) proxyFactory.getProxy();
        greeting.sayHello("Ron.Zheng");
        greeting.goodMonrning("Ron.Zheng");
    }

}
