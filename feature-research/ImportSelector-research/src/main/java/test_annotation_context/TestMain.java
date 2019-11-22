package test_annotation_context;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import test_annotation_context.biz.Circle;
import test_annotation_context.biz.Rectangle;

public class TestMain {
    public static void main( String[] args ) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext( MainConfig.class );

        Circle circle = context.getBean( Circle.class );
        circle.sayHi();
        Rectangle rectangle = context.getBean( Rectangle.class );
        rectangle.sayHi();

        context.close();
    }

}
