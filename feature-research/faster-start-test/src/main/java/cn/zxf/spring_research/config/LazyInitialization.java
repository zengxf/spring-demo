package cn.zxf.spring_research.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LazyInitialization {

    @Configuration
    public static class LazyInitBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
        @Override
        public void postProcessBeanFactory( ConfigurableListableBeanFactory beanFactory ) throws BeansException {
            for ( String beanName : beanFactory.getBeanDefinitionNames() ) {
                beanFactory.getBeanDefinition( beanName )
                        .setLazyInit( true );
            }
        }
    }

}
