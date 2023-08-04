package cn.zxf.spring_aop.spring_dump_test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import net.sf.cglib.core.DebuggingClassWriter;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) throws URISyntaxException, IOException {
        setSavePath();

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AopConfig.class); // 1

        DemoAnnotationService annotationService = context.getBean(DemoAnnotationService.class);
        DemoMethodService methodService = context.getBean(DemoMethodService.class);

        System.out.println("\n----------- 注解 -----------");
        // annotationService.add();

        System.out.println("\n----------- 方法 -----------");
        methodService.add();
        methodService.testPackage();
        methodService.testProtected();
        methodService.testPublicFinal();

        System.out.println("\n----------- 输出 -----------");
        System.out.println("DemoMethodService - proxy: " + methodService);

        context.close();
    }

    static void setSavePath() throws URISyntaxException, IOException {
        Path path = Paths.get(Main.class.getResource("/").toURI());
        Path savePath = path
                .resolve("../export/spring-aop-proxy")
                // .resolve("../main") // 打断点，也不能调试进去
                .normalize()
                .toAbsolutePath();
        Files.createDirectories(savePath);

        System.out.println("class 存放路径：");
        System.out.println(savePath);
        System.out.println("----------------------");

        // 设置将 CGLib 生成的代理类字节码生成到指定位置
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, savePath.toString());
    }

}
