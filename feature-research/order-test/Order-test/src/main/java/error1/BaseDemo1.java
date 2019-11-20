package error1;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order( 4 )
@Component
public class BaseDemo1 {
    private String name = "base demo 1";

    public BaseDemo1() {
        System.out.println( name );
    }
}
