package error_java_conf_bean_order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// order 值小的优先级高
@SpringBootApplication
public class MainApplication {

    public static void main( String[] args ) {
        SpringApplication.run( MainApplication.class, args );
    }

}
