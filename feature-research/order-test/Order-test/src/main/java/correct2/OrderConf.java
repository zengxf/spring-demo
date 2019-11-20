package correct2;

import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.context.annotation.Configuration;

@AutoConfigureOrder( 10 )
@Configuration
public class OrderConf {
    public OrderConf() {
        System.out.println( "inner order conf init!!!" );
    }
}
