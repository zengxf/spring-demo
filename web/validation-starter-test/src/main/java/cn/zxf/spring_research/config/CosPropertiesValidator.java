package cn.zxf.spring_research.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * 自定义校验器（对 @ConfigurationProperties 没用）
 * <br>
 * Created by ZXFeng on 2023/5/25
 */
@Slf4j
public class CosPropertiesValidator implements Validator {

    @Override // 没有进入
    public boolean supports(Class<?> clazz) {
        log.info("supports-clazz: [{}]", clazz);
        return CosProperties.class.isAssignableFrom(clazz);
    }

    @Override  // 没有进入
    public void validate(Object target, Errors errors) {
        // ValidationUtils.rejectIfEmpty(errors, "key1", "key1.empty");
        CosProperties value = (CosProperties) target;
        log.info("value: [{}]", value);
    }

}
