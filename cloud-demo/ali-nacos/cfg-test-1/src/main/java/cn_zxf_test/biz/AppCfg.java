package cn_zxf_test.biz;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <p/>
 * Created by ZXFeng on 2023/11/22
 */
@Component
@ConfigurationProperties(prefix = "app")    // 能刷新值
@Data
public class AppCfg {

    String sign;

}
