package cn.zxf.spring_aop.super_test;

import cn.zxf.spring_aop.spring_dump_test.DumpUtils;
import cn.zxf.spring_aop.super_test.ann.DynamicDSAspect;
import cn.zxf.spring_aop.super_test.srv.UserService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 测试父类能否继承 AOP
 * <p/>
 * ref: {@link DynamicDSAspect}
 * <br/>
 * 说明：虽然切点设置在父类，但 AOP 会在子类上创建代理
 * <p/>
 * Created by ZXFeng on 2023/11/10
 */
@Configuration
@ComponentScan("cn.zxf.spring_aop.super_test")
@EnableAspectJAutoProxy
@Slf4j
public class SuperTestMain {

    @SneakyThrows
    public static void main(String[] args) {
        DumpUtils.setSavePath();

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SuperTestMain.class);

        // String[] beanNames = context.getBeanDefinitionNames();
        // Stream.of(beanNames).forEach(beanName -> log.info("bean-name: [{}]", beanName));

        UserService userService = context.getBean(UserService.class);
        userService.test1();

        log.info("");
        userService.selectOne(8899);    // 就算重写，AOP 链也还存在

        log.info("");
        userService.selectList(8855);

        log.info("");
        userService.updateOne(5566);    // 会被 AOP，但没有拦截链路，不走拦截处理

        context.close();
    }

}
