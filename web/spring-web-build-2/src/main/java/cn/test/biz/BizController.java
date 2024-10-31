package cn.test.biz;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@RestController
@RequestMapping("/api/biz")
public class BizController {

    @Value("${test.name}")
    String testName;

    // http://localhost:9001/api/biz/hello
    @GetMapping("hello")
    public String hello() {
        return "hello --> " + testName;
    }

    /**
     * Mock 响应数据
     * <p/>
     * 调用 ref: http://localhost:9001/api/biz/mockRes
     */
    @GetMapping("mockRes")
    public BizRes mockRes() {
        return new BizRes()
                .setId(System.currentTimeMillis())
                .setBatchId("测试-OK-001")
                .setSign(testName)
                .setCreateDate(LocalDate.now())
                .setCreateTime(LocalTime.now())
                .setUpdateTime(LocalDateTime.now());
    }

}
