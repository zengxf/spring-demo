package test.java_config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * 参考：<br>
 * https://blog.mythsman.com/post/5d838c7c2db8a452e9b7082c/ <br>
 * https://mp.weixin.qq.com/s?__biz=MzIwMDY0Nzk2Mw==&mid=2650320674&idx=1&sn=837fd13a64c7b472b564eb9bdc86044e
 * <p>
 * Created by zengxf on 2019-10-23
 */
@SpringBootApplication
// @Import( { ConfigurationA.class, BeanB.class, } ) // OK
// @Import( { ConfigurationA.class, BeanB.class, ServiceA.class, } ) // OK
// @Import( { ServiceA.class, BeanB.class, BeanA.class, } ) // OK
@Import( { ServiceA.class, ConfigurationA.class, BeanB.class, } ) // ERROR
public class TestApplication {

    public static void main( String[] args ) {
        SpringApplication.run( TestApplication.class, args );
    }

}
