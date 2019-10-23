package test.java_config;

import org.springframework.beans.factory.annotation.Autowired;

public class BeanB {
    
    @Autowired
    public BeanA beanA;
    
}
