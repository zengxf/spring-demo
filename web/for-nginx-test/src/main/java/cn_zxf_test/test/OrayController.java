package cn_zxf_test.test;

import cn_zxf_test.utils.IpUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 给花生壳测试
 * <p/>
 * ZXF 创建于 2025/3/21
 */
@RestController
public class OrayController {


    @Value("${app.sign}")
    String appSign;

    /*** 基础测试 */
    // http://localhost:9901/test/oray/base
    @GetMapping("/test/oray/base")
    public String baseTest(HttpServletRequest request) {
        System.out.println("IP : " + IpUtils.clientIp(request));
        NginxController.printReq(request);
        return "hello --> " + appSign;
    }

}
