package error6;

import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

class DemoA {
    private String name = "conf demo bean A";

    DemoA() {
        System.out.println( name );
    }
}

class DemoB {
    private String name = "conf demo bean B";

    DemoB() {
        System.out.println( name );
    }
}

class DemoC {
    private String name = "conf demo bean C";

    DemoC() {
        System.out.println( name );
    }
}

@Configuration
@AutoConfigureOrder( 1 )
class AConf3 {
    @Bean
    DemoA demoA() {
        return new DemoA();
    }

    @Bean
    DemoC demoC() {
        return new DemoC();
    }
}

@Configuration
@AutoConfigureOrder( -1 )
class BConf3 {

    @Bean
    DemoB demoB() {
        return new DemoB();
    }
}
