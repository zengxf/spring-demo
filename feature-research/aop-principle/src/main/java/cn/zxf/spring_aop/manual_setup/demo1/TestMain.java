package cn.zxf.spring_aop.manual_setup.demo1;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import net.sf.cglib.core.DebuggingClassWriter;

public class TestMain {

    public static void main( String[] args ) throws Exception {
        setProxySavePath();

        Hello hello = new Hello();
        IHello proxy = (IHello) new DynamicSpringProxy().getProxy( hello );
        proxy.say();
    }

    static void setProxySavePath() throws Exception {
        System.setProperty( "jdk.proxy.ProxyGenerator.saveGeneratedFiles", "true" );

        Path path = Paths.get( TestMain.class.getResource( "/" )
                .toURI() );
        Path savePath = path.resolve( "../export/spring-aop-proxy" )
                .normalize()
                .toAbsolutePath();
        Files.createDirectories( savePath );

        System.out.println( "class 存放路径：" );
        System.out.println( savePath );
        System.out.println( "----------------------" );

        // 设置将 CGLib 生成的代理类字节码生成到指定位置
        System.setProperty( DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, savePath.toString() );
    }

}
