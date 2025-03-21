package cn_zxf_test.test;

import cn_zxf_test.utils.IpUtils;
import cn_zxf_test.utils.ResUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;

/**
 * 给 Nginx 测试
 */
@RestController
public class NginxController {

    @Value("${app.sign}")
    String appSign;

    /*** 基础测试 */
    // http://localhost:9901/test/base
    @GetMapping("/test/base")
    public String baseTest(HttpServletRequest request) {
        System.out.println("IP : " + IpUtils.clientIp(request));
        printReq(request);
        return "hello --> " + appSign;
    }


    // -------------------------------------

    /*** 用于 Nginx 添加请求头 */
    // http://localhost:9901/test/header/nginx-add-req
    @GetMapping("/test/header/nginx-add-req")
    public String testHeaderNginxAddReq(HttpServletRequest request) {
        System.out.println("IP : " + IpUtils.clientIp(request));
        printReq(request);
        return "hello --> " + appSign;
    }

    /*** 用于 Nginx 添加响应头 */
    // http://localhost:9901/test/header/nginx-add-res
    @GetMapping("/test/header/nginx-add-res")
    public String testHeaderNginxAddRes(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("IP : " + IpUtils.clientIp(request));
        printReq(request);
        response.addHeader("J2-Sign", appSign);
        return "hello --> " + appSign;
    }


    // -------------------------------------

    /*** 用于 Nginx 保持长连接 */
    // http://localhost:9901/test/nginx/keepalive
    @GetMapping("/test/nginx/keepalive")
    public String testNginxKeepalive(HttpServletRequest request) {
        System.out.println("IP : " + IpUtils.clientIp(request));
        printReq(request);
        return "hello --> " + appSign;
    }

    /*** 用于 Nginx 主从设置 */
    // http://localhost:9901/test/nginx/master-slave
    @RequestMapping("/test/nginx/master-slave")
    public String testNginxMasterSlave(HttpServletRequest request) {
        System.out.println("IP : " + IpUtils.clientIp(request));
        printReq(request);
        return "hello --> " + appSign;
    }


    // -------------------------------------

    /*** 用于 Nginx 负载均衡-最少连接 */
    // http://localhost:9901/test/nginx/lb-least-conn
    @RequestMapping("/test/nginx/lb-least-conn")
    public String testNginxLbLeastConn(HttpServletRequest request) {
        System.out.println("IP : " + IpUtils.clientIp(request));
        printReq(request);
        return "hello --> " + appSign;
    }

    /*** 用于 Nginx 负载均衡-轮询 */
    // http://localhost:9901/test/nginx/lb-round-robin
    @RequestMapping("/test/nginx/lb-round-robin")
    public String testNginxLbRoundRobin(HttpServletRequest request) {
        System.out.println("IP : " + IpUtils.clientIp(request));
        printReq(request);
        return "hello --> " + appSign;
    }

    /*** 用于 Nginx 负载均衡-随机 */
    // http://localhost:9901/test/nginx/lb-random
    @RequestMapping("/test/nginx/lb-random")
    public String testNginxLbRandom(HttpServletRequest request) {
        System.out.println("IP : " + IpUtils.clientIp(request));
        printReq(request);
        return "hello --> " + appSign;
    }

    /*** 用于 Nginx 负载均衡-权重 */
    // http://localhost:9901/test/nginx/lb-weight
    @RequestMapping("/test/nginx/lb-weight")
    public String testNginxLbWeight(HttpServletRequest request) {
        System.out.println("IP : " + IpUtils.clientIp(request));
        printReq(request);
        return "hello --> " + appSign;
    }

    /*** 用于 Nginx 负载均衡-IP哈希 */
    // http://localhost:9901/test/nginx/lb-ip-hash
    @RequestMapping("/test/nginx/lb-ip-hash")
    public String testNginxLbIpHash(HttpServletRequest request) {
        System.out.println("IP : " + IpUtils.clientIp(request));
        printReq(request);
        return "hello --> " + appSign;
    }

    /*** 用于 Nginx 负载均衡-通用哈希 */
    // http://localhost:9901/test/nginx/lb-generic-hash
    @RequestMapping("/test/nginx/lb-generic-hash/{sign}")
    public String testNginxLbGenericHash(
            HttpServletRequest request,
            @PathVariable String sign,
            @RequestParam(name = "p_sign", required = false, defaultValue = "none") String param
    ) {
        System.out.println("IP : " + IpUtils.clientIp(request));
        System.out.println("Sign : " + sign);
        System.out.println("Param : " + param);
        printReq(request);
        return "hello --> " + appSign;
    }

    /*** 用于 Nginx 限流 */
    // http://localhost:9901/test/nginx/limit
    @RequestMapping("/test/nginx/limit")
    public String testNginxLimit(HttpServletRequest request) {
        System.out.println("IP : " + IpUtils.clientIp(request));
        printReq(request);
        return "hello --> " + appSign;
    }

    /*** 用于 Nginx 限流 */
    // http://localhost:9901/test/nginx/limit2
    @RequestMapping("/test/nginx/limit2")
    public String testNginxLimit2(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("IP : " + IpUtils.clientIp(request));
        response.addHeader("J2-big-h1", ResUtils.get1k());
        printReq(request);
        return "hello --> " + appSign + " --> big-c --> " + ResUtils.get1k();
    }


    // ----------------------------------

    public static void printReq(HttpServletRequest req) {
        System.out.println("###########################################");
        System.out.println();

        System.out.println("------------------- URI -------------------");
        System.out.println(req.getProtocol());
        System.out.print(req.getMethod() + "  ");
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
