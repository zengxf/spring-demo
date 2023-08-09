package cn.zxf.spring_aop.spring_dump_test;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.aop.aspectj.AspectJAfterAdvice;
import org.springframework.aop.aspectj.AspectJAfterThrowingAdvice;
import org.springframework.aop.aspectj.AspectJAroundAdvice;
import org.springframework.aop.framework.adapter.AfterReturningAdviceInterceptor;
import org.springframework.aop.framework.adapter.MethodBeforeAdviceInterceptor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect // 1
@Component // 2
public class AopAspect {

    /*** 方法配置 - 环绕。{@link AspectJAroundAdvice} */
    @Around("execution(* cn.zxf.spring_aop.spring_dump_test.DemoMethodService.*(..))") // 6
    public Object methodAround(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        System.out.println("\n方法规则式拦截 - 环绕-Around - 01 => " + method.getName());
        Object res = null;
        try {
            res = joinPoint.proceed(); // 传递调用
        } catch (Throwable e) {
            System.out.println("出错 => " + e.getMessage());
            // throw new RuntimeException(e);
        }
        System.out.println("\n方法规则式拦截 - 环绕-Around - 02 => " + method.getName());
        System.out.println("结果 => " + res);
        return res; // 需要返回，要不然代理方法会返回 null
    }

    /*** 方法配置 - 前。{@link MethodBeforeAdviceInterceptor} */
    @Before("execution(* cn.zxf.spring_aop.spring_dump_test.DemoMethodService.*(..))") // 6
    public void methodBefore(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        System.out.println("\n方法规则式拦截 - 前-Before => " + method.getName());
    }

    /*** 方法配置 - 异常。{@link AspectJAfterThrowingAdvice} */
    @AfterThrowing("execution(* cn.zxf.spring_aop.spring_dump_test.DemoMethodService.*(..))") // 6
    public void methodAfterThrowing(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        System.out.println("\n方法规则式拦截 - 异常-AfterThrowing => " + method.getName());
    }

    /*** 方法配置 - 返回。{@link AfterReturningAdviceInterceptor} */
    @AfterReturning("execution(* cn.zxf.spring_aop.spring_dump_test.DemoMethodService.*(..))") // 6
    public void methodAfterReturning(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        System.out.println("\n方法规则式拦截 - 返回-AfterReturning => " + method.getName());
    }

    /*** 方法配置 - 后。{@link AspectJAfterAdvice} */
    @After("execution(* cn.zxf.spring_aop.spring_dump_test.DemoMethodService.*(..))") // 6
    public void methodAfter(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        System.out.println("\n方法规则式拦截 - 后-After => " + method.getName());
    }

    // -----------------

    /*** 注解配置 */
    @Pointcut("@annotation(cn.zxf.spring_aop.spring_dump_test.Action)") // 3
    public void annotationPointCut() {
    }

    /**
     * {@link #annotationPointCut()}
     */
    @After("annotationPointCut()") // 4
    public void annotationAfter(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Action action = method.getAnnotation(Action.class);
        System.out.println("注解式拦截-方法执行后 => " + action.name()); // 5
    }

}
