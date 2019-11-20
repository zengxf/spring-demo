package error_ordered;

import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
public class BaseDemo3 implements Ordered {
    private String name = "base demo 3";

    public BaseDemo3() {
        System.out.println( name );
    }

    @Override
    public int getOrder() {
        return 4;
    }
}
