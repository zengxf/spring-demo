package error_order;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order( 3 )
@Component
public class BaseDemo2 {
    private String name = "base demo 2";

    public BaseDemo2() {
        System.out.println( name );
    }
}
