package cn.zxf.spring_aop.manual_setup.demo1;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class SurroundAdvice implements MethodInterceptor {
    @Override
    public Object invoke( MethodInvocation invocation ) throws Throwable {
        System.out.println( "------------------surround before---------------------" );
        Object result = invocation.proceed();
        System.out.println( "------------------surround after----------------------" );
        return result;
    }
}