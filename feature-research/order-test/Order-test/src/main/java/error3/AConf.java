package error3;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Order( 1 )
@Configuration
class AConf {
    public AConf() {
        System.out.println( "AConf init!" );
    }
}

@Order( 0 )
@Configuration
class BConf {
    public BConf() {
        System.out.println( "BConf init" );
    }
}