package correct1;

import java.util.List;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

interface IBean {
}

@Order( 2 )
@Component
class AnoBean1 implements IBean {

    private String name = "ano order bean 1";

    AnoBean1() {
        System.out.println( name );
    }
}

@Order( 1 )
@Component
class AnoBean2 implements IBean {

    private String name = "ano order bean 2";

    AnoBean2() {
        System.out.println( name );
    }
}

@Component
class AnoTestBean {

    public AnoTestBean( List<IBean> anoBeanList ) {
        for ( IBean bean : anoBeanList ) {
            System.out.println( "in ano testBean: " + bean.getClass()
                    .getName() );
        }
    }
}
