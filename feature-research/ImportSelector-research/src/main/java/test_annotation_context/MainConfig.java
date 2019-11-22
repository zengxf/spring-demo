package test_annotation_context;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import test_annotation_context.biz.Circle;

@Import( { Circle.class, MyImportRegistrar.class } )
@Configuration
public class MainConfig {

}
