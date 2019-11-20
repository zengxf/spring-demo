package conf1;

import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.context.annotation.Configuration;

@AutoConfigureOrder( 1 )
@Configuration
public class AdditionOrderConf {
    public AdditionOrderConf() {
        System.out.println( "additionOrderConf init!!!" );
    }
}
