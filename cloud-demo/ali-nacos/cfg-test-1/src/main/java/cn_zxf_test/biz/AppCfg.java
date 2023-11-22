package cn_zxf_test.biz;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <p/>
 * 曾献峰(656553) 创建于 2023/11/22
 */
@Component
@ConfigurationProperties(prefix = "app")    // 能刷新值
@Data
public class AppCfg {

    String sign;

}
