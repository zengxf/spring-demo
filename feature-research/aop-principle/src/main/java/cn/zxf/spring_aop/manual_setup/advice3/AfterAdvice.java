package cn.zxf.spring_aop.manual_setup.advice3;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

public class AfterAdvice implements AfterReturningAdvice {
    @Override
    public void afterReturning( Object returnValue, Method method, Object[] args, Object target ) throws Throwable {
        System.out.println( "------------------after------------------" );
    }
}