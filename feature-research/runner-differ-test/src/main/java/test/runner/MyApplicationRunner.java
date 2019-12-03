package test.runner;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import java.util.Arrays;

@Component
public class MyApplicationRunner implements ApplicationRunner {
    @Override
    public void run( ApplicationArguments args ) throws Exception {
        System.out.println( "===MyApplicationRunner===" + Arrays.asList( args.getSourceArgs() ) );
        System.out.println( "===getOptionNames========" + args.getOptionNames() );
        System.out.println( "===getOptionValues=======" + args.getOptionValues( "name" ) );
        System.out.println( "===getOptionValues=======" + args.getOptionValues( "age" ) );
    }
}