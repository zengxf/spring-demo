package cn.zxf.spring_aop.spring_order_test;

import org.springframework.aop.aspectj.AspectJAfterAdvice;
import org.springframework.aop.aspectj.AspectJAroundAdvice;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 断点调试：org.springframework.aop.framework.ReflectiveMethodInvocation #proceed() 时，<br/>
 * 会按顺序一个一个执行，只是输出有些不一致，<br/>
 * 是因为 {@link AspectJAroundAdvice}、{@link AspectJAfterAdvice} 等拦截器内部处理会按对应逻辑进行传递，导致仅看输出时会不一致。
 */
public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AopConfig.class);

        DemoService service = context.getBean(DemoService.class);

        System.out.println("----------------------");
        service.add();

        System.out.println("----------------------");
        System.out.println("----------------------");
        // service.update("zxf-01", "zxf-666");

        System.out.println("----------------------");
        System.out.println("----------------------");
        try {
            // service.error();
        } catch (Exception e) {
        }

        System.out.println("----------------------");
        context.close();
    }

}
