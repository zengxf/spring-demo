package cn.zxf.spring_research.config;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <br>
 * Created by ZXFeng on 2023/5/25
 */
// @org.springframework.stereotype.Component // 与 @Configuration 一样，可注入
// @org.springframework.context.annotation.Configuration // 与 @Component 一样，可注入
// @jakarta.validation.Valid // 不校验
// @org.springframework.validation.annotation.Validated // 会校验，但没输出错误消息
@ConfigurationProperties("tx-cloud.cos") // 不能使用大写
@Data
public class CosPropertiesBck {

    @NotEmpty(message = "腾讯云 COS 认证 ID 不能为空")
    private String secretId;
    @NotEmpty(message = "腾讯云 COS 认证 Key 不能为空")
    private String secretKey;
    @NotEmpty(message = "腾讯云 COS Bucket 不能为空")
    private String bucketName;
    @NotEmpty(message = "腾讯云 COS Region 不能为空")
    private String region;

    private String cosPath;

}

