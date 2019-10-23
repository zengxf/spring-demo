package test.java_config;

import org.springframework.beans.factory.annotation.Autowired;

public class ServiceA {

    @Autowired
    BeanA beanA;

    @Autowired
    BeanB beanB; // 在上面 OK

}
