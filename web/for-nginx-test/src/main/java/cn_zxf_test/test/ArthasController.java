package cn_zxf_test.test;

import cn_zxf_test.utils.IpUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Ali Arthas 测试
 * <p/>
 * ZXF 创建于 2025/4/2
 */
@RestController
public class ArthasController {

    @Value("${app.sign}")
    String appSign;

    /*** 基础测试 */
    // http://localhost:9901/test/arthas/base
    @GetMapping("/test/arthas/base")
    public Map<String, Object> baseTest(HttpServletRequest request) {
        System.out.println("IP : " + IpUtils.clientIp(request));

        Map<String, Object> res = new LinkedHashMap<>();
        res.put("appSign", appSign);
        res.put("date", LocalDate.now());
        res.put("time", LocalTime.now());
        res.put("base-test-sign", "v1-222");
        return res;
    }

}
