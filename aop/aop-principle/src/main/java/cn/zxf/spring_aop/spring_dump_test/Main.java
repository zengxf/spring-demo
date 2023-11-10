package cn.zxf.spring_aop.spring_dump_test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.net.URISyntaxException;

public class Main {

    public static void main(String[] args) throws URISyntaxException, IOException {
        DumpUtils.setSavePath();

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AopConfig.class);

        DemoAnnotationService annotationService = context.getBean(DemoAnnotationService.class);
        DemoMethodService methodService = context.getBean(DemoMethodService.class);

        System.out.println("\n----------- 注解 -----------");
        // annotationService.add();

        System.out.println("\n----------- 方法 -----------");
        // methodService.add();
        // methodService.testPackage();
        // methodService.testProtected();
        // methodService.testPublicFinal();
        // methodService.testError();
        Integer v = methodService.testReturn();
        System.out.println("v ==> " + v);

        System.out.println("\n----------- 输出 -----------");
        System.out.println("DemoMethodService - proxy: " + methodService);

        context.close();
    }

}
