package cn.zxf.spring_research.config;

import jakarta.validation.Valid;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <br>
 * Created by ZXFeng on 2023/5/25
 */
@Component
// @Configuration
@ConfigurationProperties("tx-cloud.cos")
@Validated
@Data
public class CosProperties {

    @NotEmpty(message = "腾讯云 COS 认证 ID 不能为空")
    private String secretId;
    @NotEmpty(message = "腾讯云 COS 认证 Key 不能为空")
    private String secretKey;
    @NotEmpty(message = "腾讯云 COS Bucket 不能为空")
    private String bucketName;
    @NotEmpty(message = "腾讯云 COS Region 不能为空")
    private String region;
    private String cosPath;

    @Valid
    private CosPropAppend append;

}
