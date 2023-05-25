package cn.zxf.spring_research.custom_validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = {ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CheckIntegerIntervalValidator.class)
public @interface CheckIntegerInterval {

    String startIndex() default "start";

    String endIndex() default "end";

    String message() default "数字范围不合要求";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
