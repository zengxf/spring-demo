package cn.zxf.spring_aop.super_test.ann;

import java.lang.annotation.*;

/**
 * <br>
 * Created by ZXFeng on 2023/11/10
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DynamicDS {

    String value();

}
