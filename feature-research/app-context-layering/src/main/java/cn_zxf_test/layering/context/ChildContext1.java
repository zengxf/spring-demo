package cn_zxf_test.layering.context;

import cn_zxf_test.layering.bean.ChildBean;
import cn_zxf_test.layering.bean.PartBean;
import cn_zxf_test.layering.bean.RootBean;
import cn_zxf_test.layering.config.YamlPropertyLoaderFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

/**
 * <p/>
 * Created by ZXFeng on 2024/1/23
 */
@SpringBootApplication(scanBasePackages = {"cn_zxf_test.layering.controller"})
@PropertySource(value = "classpath:/bean-config-1.yaml", factory = YamlPropertyLoaderFactory.class)
public class ChildContext1 {

    @Bean
    public ChildBean getChildBean(
            @Value("${spring.application.name}") String name,
            RootBean fatherBean
    ) {
        ChildBean childBean = new ChildBean();
        childBean.setName(name);
        childBean.setFatherBean(fatherBean);
        return childBean;
    }

    @Bean("partBean")
    public PartBean getPartBean() {
        return new PartBean("child-1-test");
    }

}