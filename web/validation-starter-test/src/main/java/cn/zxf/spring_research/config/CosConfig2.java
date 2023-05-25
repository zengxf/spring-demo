package cn.zxf.spring_research.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;

/**
 * <br>
 * Created by ZXFeng on 2023/5/25
 */
@Configuration
@Slf4j
public class CosConfig2 {

    @Bean // 将方法声明为 static 允许该 Bean 在创建时不需要实例化 @Configuration 类
    public static Validator cosPropertiesValidator() {
        log.info("创建自定义校验器......");
        return new CosPropertiesValidator(); // 能创建，但不进行校验
    }

}
