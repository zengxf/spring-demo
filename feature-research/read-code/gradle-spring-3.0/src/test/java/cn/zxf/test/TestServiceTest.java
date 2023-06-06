package cn.zxf.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Slf4j
public class TestServiceTest {

    public static void main(String[] args) {
        log.info("--------------------------------");

        String path = "application.xml";
        ApplicationContext context = new ClassPathXmlApplicationContext(path);

        // TestService service = (TestService) context.getBean("testService");
        TestService service = context.getBean(TestService.class);
        String result = service.test("admin");

        log.info("result : " + result);
    }

}