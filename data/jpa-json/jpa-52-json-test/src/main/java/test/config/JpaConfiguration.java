package test.config;

import com.vladmihalcea.hibernate.type.util.Configuration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * <br/>
 * Created by ZXFeng on 2022/3/11.
 */
@Component
public class JpaConfiguration implements HibernatePropertiesCustomizer {

    @Override
    public void customize(Map<String, Object> hibernateProperties) {
        // hibernateProperties.put(Configuration.PropertyKey.JACKSON_OBJECT_MAPPER.getKey(),
        //         MyObjectMapperSupplier.class.getName());
        System.getProperties().put(Configuration.PropertyKey.JACKSON_OBJECT_MAPPER.getKey(),
                MyObjectMapperSupplier.class.getName());
    }

}
