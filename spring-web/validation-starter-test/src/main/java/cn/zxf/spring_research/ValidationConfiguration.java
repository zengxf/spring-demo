package cn.zxf.spring_research;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidationConfiguration {

    @Bean
    public Validator validator() {
        ValidatorFactory vf = Validation.byProvider( HibernateValidator.class )
                .configure()
                .failFast( true )
                .buildValidatorFactory();
        return vf.getValidator();
    }

}
