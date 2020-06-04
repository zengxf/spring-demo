package cn.zxf.starter.error;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MyExceptionHandler {

    @ResponseStatus( HttpStatus.INTERNAL_SERVER_ERROR )
    @ExceptionHandler( { Exception.class } )
    public Map<String, Object> processException( Exception e ) {
        e.printStackTrace();
        Map<String, Object> json = new HashMap<>();
        json.put( "code", "100401" );
        json.put( "desc", "内部出错" );
        return json;
    }

}
