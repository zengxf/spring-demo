package cn.zxf.spring_aop.super_test.ann;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * <br>
 * Created by ZXFeng on 2023/11/10
 */
@Aspect
@Component
@Slf4j
public class DynamicDSAspect {

    /*** 注解配置 */
    @Pointcut("@annotation(cn.zxf.spring_aop.super_test.ann.DynamicDS)")
    public void annotationPointCut() {
    }

    /*** {@link #annotationPointCut()} */
    @Before("annotationPointCut()")
    public void annotationBefore(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        DynamicDS action = method.getAnnotation(DynamicDS.class);
        log.info("");
        log.info("注解式拦截");
        log.info("方法 => [{}]", method.getName());
        log.info("注解式拦截-方法执行前 => [{}]", action.value());
        log.info("----------------------");
    }

    // ----------------------------
    // ----------------------------

    /*** 表达式配置 */
    @Pointcut("execution(* cn.zxf.spring_aop.super_test.srv.BaseService.select*(..))")
    public void expPointCut() {
    }

    /*** {@link #expPointCut()} */
    @Before("expPointCut()")
    public void expBefore(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        log.info("");
        log.info("表达式拦截");
        log.info("方法 => [{}]", method.getName());
        log.info("----------------------");
    }

}
