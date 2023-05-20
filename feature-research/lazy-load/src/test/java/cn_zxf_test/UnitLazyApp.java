package cn_zxf_test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

/**
 * 用于单元测试（使用懒加载）
 * <br>
 * Created by ZXFeng on 2023/5/19
 */
@SpringBootApplication
@ComponentScan(
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.ASSIGNABLE_TYPE,
                classes = {
                        MainApplication.class, UnitNotLazyApp.class,
                }
        ),
        lazyInit = true
)
@Slf4j
public class UnitLazyApp {
}