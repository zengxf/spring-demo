package cn.zxf.spring_aop.manual_setup.demo1;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class BeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before( Method method, Object[] args, Object target ) throws Throwable {
        System.out.println( "------------------before------------------" );
    }
}