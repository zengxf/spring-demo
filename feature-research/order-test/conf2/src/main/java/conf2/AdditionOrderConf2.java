package conf2;

import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.context.annotation.Configuration;

@Configuration
@AutoConfigureOrder( -1 )
public class AdditionOrderConf2 {
    public AdditionOrderConf2() {
        System.out.println( "additionOrderConf2 init!!!" );
    }
}
