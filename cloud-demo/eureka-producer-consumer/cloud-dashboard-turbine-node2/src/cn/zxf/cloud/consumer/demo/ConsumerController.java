package cn.zxf.cloud.consumer.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ConsumerController {

    @Autowired
    HelloRemote helloRemote;

    @RequestMapping( "/hello/{name}" )
    public String index( //
            @PathVariable( "name" ) String name //
    ) {
	String message = helloRemote.hello( name );
	log.info( "hello message: {}", message );
	return message;
    }

    @RequestMapping( "/hello2/{name}" )
    public String index2( //
            @PathVariable( "name" ) String name //
    ) {
	String message = helloRemote.hello2( name );
	log.info( "hello message: {}", message );
	return message;
    }

    @RequestMapping( "/hello3/{name}" )
    public String index3( //
            @PathVariable( "name" ) String name //
    ) {
	String message = helloRemote.hello3( name );
	log.info( "hello message: {}", message );
	return message;
    }

}