package test.cfg1;

import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

@DependsOn( "rightDemo2" )
@Component
class RightDemo1 {
    private String name = "right demo 1";

    public RightDemo1() {
        System.out.println( name );
    }
}

@Component
class RightDemo2 {
    private String name = "right demo 2";

    public RightDemo2() {
        System.out.println( name );
    }
}