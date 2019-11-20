package correct_ordered;

import java.util.List;

import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

interface IBean {
}

@Component
class AnoBean1 implements IBean, Ordered {

    private String name = "ano order bean 1";

    AnoBean1() {
        System.out.println( name );
    }

    @Override
    public int getOrder() {
        return 2;
    }
}

@Component
class AnoBean2 implements IBean, Ordered {

    private String name = "ano order bean 2";

    AnoBean2() {
        System.out.println( name );
    }

    @Override
    public int getOrder() {
        return 1;
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
