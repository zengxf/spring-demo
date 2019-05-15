package cn.zxf.spring_research;

import org.springframework.context.annotation.Configuration;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;

@Configuration
@EnableApolloConfig( { "ns1", "ns2", "application.yml" } )
public class ApolloConfiguration {
}