package test.biz;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.DebuggingClassWriter;

import lombok.extern.slf4j.Slf4j;
import test.AbstractApplicationTest;

@Slf4j
public class TestBizService extends AbstractApplicationTest {

    @Autowired
    private BizService service;

    @BeforeClass
    public static void setProxySavePath() throws Exception {
        System.setProperty( "jdk.proxy.ProxyGenerator.saveGeneratedFiles", "true" );

        Path path = Paths.get( TestBizService.class.getResource( "/" )
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

    @Test
    public void hello() {
        service.hello();
        log.info( "ok!" );
    }

    @Test
    public void findInfo() {
        Map<String, Object> map = service.findInfo();
        log.info( "map: {}", map );
        log.info( "-------------------" );

        map = service.findInfo();
        log.info( "map: {}", map );
    }

    @Test
    public void findInfo11() {
        Map<String, Object> map = service.findInfo11();
        log.info( "map: {}", map );
        log.info( "-------------------" );

        map = service.findInfo11();
        log.info( "map: {}", map );
    }

}
