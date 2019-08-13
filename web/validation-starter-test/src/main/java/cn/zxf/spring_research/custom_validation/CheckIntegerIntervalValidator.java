package cn.zxf.spring_research.custom_validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class CheckIntegerIntervalValidator implements ConstraintValidator<CheckIntegerInterval, Object> {

    private String startIndex, endIndex;

    @Override
    public void initialize( CheckIntegerInterval constraintAnnotation ) {
        ConstraintValidator.super.initialize( constraintAnnotation );
        startIndex = constraintAnnotation.startIndex();
        endIndex = constraintAnnotation.endIndex();
    }

    @Override
    public boolean isValid( Object value, ConstraintValidatorContext context ) {
        if ( value == null )
            return true;
        BeanWrapper bean = new BeanWrapperImpl( value );
        Object start = bean.getPropertyValue( startIndex );
        Object end = bean.getPropertyValue( endIndex );
        if ( start == null || end == null )
            return true;
        int result = ( (Integer) end ).compareTo( (Integer) start );
        return result > 0;
    }

}
