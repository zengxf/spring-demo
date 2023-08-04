package cn.zxf.spring_aop.manual_setup;

import java.lang.reflect.Method;
import java.util.Objects;

import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.aop.PointcutAdvisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.StaticMethodMatcher;

public class ManualSetupTest {

    public static void main( String[] args ) {
        // 1. 构造目标对象
        Cat catTarget = new Cat();
        // 2. 通过目标对象，构造 ProxyFactory 对象
        ProxyFactory factory = new ProxyFactory( catTarget );
        // 添加一个方法拦截器
        factory.addAdvice( new MyMethodInterceptor() );
        MyPointcutAdvisor myPointcutAdvisor = new MyPointcutAdvisor();
        factory.addAdvisor( myPointcutAdvisor );
        // 3. 根据目标对象生成代理对象
        Object proxy = factory.getProxy();

        Animal cat = (Animal) proxy;
        cat.eat();
        System.out.println( "---------------------------------------" );
        cat.go();
    }

    public static class MyPointcut implements Pointcut {
        @Override
        public ClassFilter getClassFilter() {
            return ClassFilter.TRUE; // 匹配所有的类
        }

        @Override
        public MethodMatcher getMethodMatcher() {
            // 继承 StaticMethodMatcher，忽略方法实参，只对方法进行静态匹配
            return new StaticMethodMatcher() {
                // boolean isRuntime(); // 返回 true，则进行动态匹配
                // boolean matches(Method method, Class<?> targetClass, Object... args); // 动态匹配，即对实参进行校验
                @Override
                public boolean matches( Method method, Class<?> targetClass ) { // 静态匹配
                    return Objects.equals( "go", method.getName() ); // 限定 go 方法名
                }
            };
        }
    }

    public static class MyPointcutAdvisor implements PointcutAdvisor {
        private Pointcut pointcut = new MyPointcut();
        private Advice   advice   = new MyMethodInterceptor();

        @Override
        public Pointcut getPointcut() {
            return this.pointcut;
        }

        @Override
        public Advice getAdvice() {
            return this.advice;
        }

        @Override
        public boolean isPerInstance() {
            return true;
        }
    }

    public static class MyMethodInterceptor implements MethodInterceptor {
        @Override
        public Object invoke( MethodInvocation invocation ) throws Throwable {
            System.out.println( "MyMethodInterceptor invoke 调用 before invocation.proceed - " + this );
            Object ret = invocation.proceed();
            System.out.println( "MyMethodInterceptor invoke 调用 after invocation.proceed - " + this );
            return ret;
        }
    }

    public static interface Animal {
        void eat();

        void go();
    }

    public static class Cat implements Animal {
        @Override
        public void eat() {
            System.out.println( "猫吃鱼" );
        }

        @Override
        public void go() {
            System.out.println( "猫在跑" );
        }
    }

}
