package cn.zxf.bean_get.test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class MoonBookConfiguration {

    // 如果一个bean依赖另一个bean，则直接调用对应JavaConfig类中依赖bean的创建方法即可
    @Bean
    public BookService bookService() {
        return new BookService( dependencyService() );
    }

    @Bean
    public OtherService otherService() {
        return new OtherService( dependencyService() );
    }

    // 通过 CGLib 代理，只调用一次
    @Bean
    public DependencyService dependencyService() {
        System.out.println( "==================== dependencyService() ======================" );
        log.info("============ dependencyService() xxxxxxxxxxx");
        return new DependencyService();
    }

}
