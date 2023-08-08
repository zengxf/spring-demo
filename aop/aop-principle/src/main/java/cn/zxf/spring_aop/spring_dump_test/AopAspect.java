package cn.zxf.spring_aop.spring_dump_test;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect // 1
@Component // 2
public class AopAspect {

    /*** 方法配置 */
    @Before("execution(* cn.zxf.spring_aop.spring_dump_test.DemoMethodService.*(..))") // 6
    public void methodBefore(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        System.out.println("\n方法规则式拦截 => " + method.getName());
    }

    /*** 方法配置 - 后 */
    // @After("execution(* cn.zxf.spring_aop.spring_dump_test.DemoMethodService.*(..))") // 6
    public void methodAfter(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        System.out.println("\n方法规则式拦截 => " + method.getName());
    }

    /*** 方法配置 - 环绕 */
    // @Around("execution(* cn.zxf.spring_aop.spring_dump_test.DemoMethodService.*(..))") // 6
    public void methodAround(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        System.out.println("\n方法规则式拦截 => " + method.getName());
    }

    /*** 方法配置 - 异常 */
    // @AfterThrowing("execution(* cn.zxf.spring_aop.spring_dump_test.DemoMethodService.*(..))") // 6
    public void methodAfterThrowing(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        System.out.println("\n方法规则式拦截 => " + method.getName());
    }

    /*** 方法配置 - 返回 */
    // @AfterReturning("execution(* cn.zxf.spring_aop.spring_dump_test.DemoMethodService.*(..))") // 6
    public void methodAfterReturning(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        System.out.println("\n方法规则式拦截 => " + method.getName());
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
