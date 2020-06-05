package cn.zxf.consumer;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cglib.core.DebuggingClassWriter;

@SpringBootApplication
public class Consumer1MainApplication {

    public static void main( String[] args ) throws Exception {
        setProxySavePath();
        System.setProperty( "spring.profiles.active", "consumer1" );
        SpringApplication.run( Consumer1MainApplication.class, args );
    }

    static void setProxySavePath() throws Exception {
        System.setProperty( "jdk.proxy.ProxyGenerator.saveGeneratedFiles", "true" );

        Path path = Paths.get( Consumer1MainApplication.class.getResource( "/" )
                .toURI() );
        Path savePath = path.resolve( "../export/spring-aop-proxy" )
                .normalize()
                .toAbsolutePath();
        Files.createDirectories( savePath );

        System.out.println( "class 存放路径：" );
        System.out.println( savePath );
        System.out.println( "----------------------" );

        // 设置将 cglib 生成的代理类字节码生成到指定位置
        System.setProperty( DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, savePath.toString() );
    }

}
