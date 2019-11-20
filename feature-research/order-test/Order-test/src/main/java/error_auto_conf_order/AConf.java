package error_auto_conf_order;

import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.context.annotation.Configuration;


@Configuration
@AutoConfigureOrder( 1 )
class AConf2 {
    public AConf2() {
        System.out.println( "A Conf2 init!" );
    }
}

@Configuration
@AutoConfigureOrder( -1 )
class BConf2 {
    public BConf2() {
        System.out.println( "B conf2 init!" );
    }
}
