package cn_zxf_test.layering.context;

import cn_zxf_test.layering.bean.RootBean;
import cn_zxf_test.layering.config.YamlPropertyLoaderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * <p/>
 * Created by ZXFeng on 2024/1/23
 */
@Configuration
@PropertySource(value = "classpath:/root.yaml", factory = YamlPropertyLoaderFactory.class)
public class RootContext {

    @Bean
    public RootBean getFatherBean() {
        RootBean rootBean = new RootBean();
        rootBean.setName("root");
        return rootBean;
    }

}