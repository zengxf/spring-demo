package correct_ordered;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import conf1.AdditionOrderConf;
import conf2.AdditionOrderConf2;

// order 值小的优先级高
@SpringBootApplication( exclude = { AdditionOrderConf.class, AdditionOrderConf2.class } )
public class MainApplication {

    public static void main( String[] args ) {
        SpringApplication.run( MainApplication.class, args );
    }

}
