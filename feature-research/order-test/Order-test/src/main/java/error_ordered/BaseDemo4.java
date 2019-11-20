package error_ordered;

import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
public class BaseDemo4 implements Ordered {
    private String name = "base demo 4";

    public BaseDemo4() {
        System.out.println( name );
    }

    @Override
    public int getOrder() {
        return 3;
    }
}
