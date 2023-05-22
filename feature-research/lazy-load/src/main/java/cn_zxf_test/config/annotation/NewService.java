package cn_zxf_test.config.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * 自定义注解
 * <br>
 * Created by ZXFeng on 2023/5/20
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface NewService {

    /*** 名称 */
    String name() default "";

}
