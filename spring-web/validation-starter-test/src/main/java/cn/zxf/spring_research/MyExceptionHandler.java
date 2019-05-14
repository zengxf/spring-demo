package cn.zxf.spring_research;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MyExceptionHandler {

    @ResponseStatus( HttpStatus.BAD_REQUEST )
    @ExceptionHandler( value = ConstraintViolationException.class )
    public Map<String, Object> errorHandle( ConstraintViolationException ex ) {
        Map<String, Object> json = new HashMap<>();
        json.put( "sign", "ConstraintViolationException" );
        json.put( "error", "参数异常" );
        String errorMessage = ex.getConstraintViolations()
                .stream()
                .map( ConstraintViolation::getMessage )
                .collect( Collectors.joining( "；" ) );
        json.put( "message", errorMessage );
        return json;
    }

    @ResponseStatus( HttpStatus.BAD_REQUEST )
    @ExceptionHandler( value = MethodArgumentNotValidException.class )
    public Map<String, Object> errorHandle( MethodArgumentNotValidException ex ) {
        Map<String, Object> json = new HashMap<>();
        json.put( "sign", "MethodArgumentNotValidException" );
        json.put( "error", "参数异常" );
        String errorMessage = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map( ObjectError::getDefaultMessage )
                .collect( Collectors.joining( "；" ) );
        json.put( "message", errorMessage );
        return json;
    }

}
