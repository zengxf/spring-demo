package cn.zxf.cloud.producer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.zxf.cloud.producer.controller.simple.SimpleDto;

@RestController
public class HelloController {

    @RequestMapping( "/hello" )
    public String index( @RequestParam String name ) {
	if ( System.currentTimeMillis() % 5 == 0 ) {
	    throw new RuntimeException( "zxf random error !!!" );
	}
	return "hello " + name + "，this is tow(2) messge";
    }

    @GetMapping( "/simple1" )
    public SimpleDto simple1() {
	return SimpleDto.builder()//
	        .age( 888888 )//
	        .name( "zxf - " + 2 )//
	        .build();
    }

}