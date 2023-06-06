package cn.zxf.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestServiceTest {

    public static void main(String[] args) {
        System.out.println("--------------------------------");
        // String path = TestServiceTest.class.getResource("application.xml").getPath();
        String path = "application.xml";
        ApplicationContext context = new ClassPathXmlApplicationContext(path);
        TestService service = (TestService) context.getBean("testService");
        String result = service.test("admin");
        System.out.println("result : " + result);
    }

}