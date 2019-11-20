package error_java_conf_bean_order;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

class Demo1 {
    private String name = "conf demo bean 1";

    public Demo1() {
        System.out.println( name );
    }
}

class Demo2 {
    private String name = "conf demo bean 2";

    public Demo2() {
        System.out.println( name );
    }
}

class Demo3 {
    private String name = "conf demo bean 3";

    public Demo3() {
        System.out.println( name );
    }
}

@Order( 2 )
@Configuration
class AConf1 {
    @Bean
    public Demo1 demo1() {
        return new Demo1();
    }

    @Bean
    public Demo3 demo3() {
        return new Demo3();
    }
}

@Order( 1 )
@Configuration
class BConf1 {
    @Bean
    public Demo2 demo2() {
        return new Demo2();
    }
}
