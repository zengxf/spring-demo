package test.cfg2;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

@DependsOn( "rightDemo2" )
@Component
class RightDemo1 {
    private String name = "** right demo ** 1";

    @Autowired
    RightDemo2     rightDemo2;

    public RightDemo1() {
        System.out.println( name );
    }

    @PostConstruct
    public void init() {
        System.out.println( name + " _init" );
    }
}

@Component
class RightDemo2 {
    private String name = "** right demo ** 2";

    @Autowired
    RightDemo1     rightDemo1;

    public RightDemo2() {
        System.out.println( name );
    }

    @PostConstruct
    public void init() {
        System.out.println( name + " _init" );
    }
}
