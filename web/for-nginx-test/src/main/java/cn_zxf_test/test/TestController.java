package cn_zxf_test.test;

import cn_zxf_test.utils.IpUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;

@RestController
public class TestController {

    @Value("${app.sign}")
    String appSign;

    // http://localhost:9901/test/base
    @GetMapping("/test/base")
    public String baseTest(HttpServletRequest request) {
        System.out.println("IP : " + IpUtils.clientIp(request));
        printReq(request);
        return "hello --> " + appSign;
    }

    // http://localhost:9901/test/header/nginx-add
    @GetMapping("/test/header/nginx-add")
    public String testHeaderNginxAdd(HttpServletRequest request) {
        System.out.println("IP : " + IpUtils.clientIp(request));
        printReq(request);
        return "hello --> " + appSign;
    }

    // ----------------------------------

    private static void printReq(HttpServletRequest req) {
        System.out.println("###########################################");
        System.out.println();

        System.out.println("------------------- URI -------------------");
        System.out.println(req.getMethod());
        System.out.println(req.getRequestURI());
        // System.out.println(req.getQueryString());

        System.out.println("------------------- Header -------------------");
        Enumeration<String> hNames = req.getHeaderNames();
        while (hNames.hasMoreElements()) {
            String hName = hNames.nextElement();
            String hValue = req.getHeader(hName);
            System.out.println(hName + " : " + hValue);
        }

        System.out.println("-------------------- Body -------------------");
        // 读取请求体 (JSON 格式)
        StringBuilder body = new StringBuilder();
        try (BufferedReader reader = req.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                body.append(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(body);
        System.out.println();
    }

}
