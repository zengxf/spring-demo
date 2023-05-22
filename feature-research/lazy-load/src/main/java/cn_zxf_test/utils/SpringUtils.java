package cn_zxf_test.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

import java.util.stream.IntStream;

/**
 * <br>
 * Created by ZXFeng on 2023/5/20
 */
@Configuration
@Slf4j
public class SpringUtils implements ApplicationContextAware {

    public static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    public static <T> T getBean(Class<T> type) {
        return context.getBean(type);
    }

    public static void showAllBean() {
        log.info("bean-size: [{}]", context.getBeanDefinitionCount());
        String[] names = context.getBeanDefinitionNames();
        IntStream.rangeClosed(1, names.length)
                .forEach(i -> log.info("{}. bean-name: [{}]", i, names[i - 1]));
    }

}
