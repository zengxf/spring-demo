package cn.zxf.spring_aop.manual_setup;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class TestCglibProxy {
    public static class Service {
        /*** final 方法不能被子类覆盖 */
        public final void finalMethod() {
            System.out.println( "Service.finalMethod 执行了" );
        }

        public void publicMethod() {
            System.out.println( "Service.publicMethod 执行了" );
        }
    }

    public static class MyCglibDynamicProxy implements MethodInterceptor {
        /** * 目标对象（也被称为被代理对象） */
        private Object target;

        public MyCglibDynamicProxy( Object target ) {
            this.target = target;
        }

        @Override
        public Object intercept( Object obj, Method method, Object[] args, MethodProxy proxy ) throws Throwable {
            System.out.println( "CglibDynamicProxy intercept 方法执行前-------------------------------" );
            System.out.println( "obj = " + obj.getClass() );
            System.out.println( "method = " + method );
            System.out.println( "proxy = " + proxy );
            Object object = proxy.invoke( target, args );
            System.out.println( "CglibDynamicProxy intercept 方法执行后-------------------------------" );
            return object;
        }

        /** * 获取被代理接口实例对象。通过 enhancer.create 可以获得一个代理对象，它继承了 target.getClass() 类 */
        @SuppressWarnings( "unchecked" )
        public < T > T getMyProxy() {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass( target.getClass() );
            enhancer.setCallback( this );
            return (T) enhancer.create(); // 以此创建
        }
    }

    public static void main( String[] args ) throws URISyntaxException, IOException {
        setSavePath();
        // 1. 构造目标对象
        Service target = new Service();
        // 2. 根据目标对象生成代理对象
        MyCglibDynamicProxy proxy = new MyCglibDynamicProxy( target );
        // 获取 CGLIB 代理类
        Service proxyObject = proxy.getMyProxy();
        // 调用代理对象的方法
        System.out.println( "finalMethod 开始执行：" );
        proxyObject.finalMethod();
        System.out.println( "================\n" );
        System.out.println( "publicMethod 开始执行：" );
        proxyObject.publicMethod();
    }

    static void setSavePath() throws URISyntaxException, IOException {
        Path savePath = Paths.get( System.getProperty( "user.dir" ) + "/export/cglib" )
                .normalize()
                .toAbsolutePath();
        Files.createDirectories( savePath );

        System.out.println( "CGLib-class 存放路径：" );
        System.out.println( savePath );
        System.out.println( "----------------------" );

        // 设置将 CGLib 生成的代理类字节码生成到指定位置
        System.setProperty( DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, savePath.toString() );
    }

}
