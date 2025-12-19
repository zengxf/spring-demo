package cn_zxf_test.mock;

import cn_zxf_test.biz.TimeUtils;
import cn_zxf_test.utils.HttpUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p/>
 * Created by ZXFeng on 2025/12/19
 */
@RestController
@RequestMapping("/mock/http")
public class MockHttpController {
    String BAIDU_BASE_URL = "https://www.baidu.com";

    // http://localhost:9001/mock/http/baidu
    @GetMapping("baidu")
    public Map<String, Object> baidu() {
        String v = String.format("%s.%s", System.currentTimeMillis(), System.nanoTime());
        String url = String.format("%s?v=%s", BAIDU_BASE_URL, v);
        String result = HttpUtils.doGet(url);
        return Map.of(
                "v", v,
                "result", result,
                "thread", Thread.currentThread().toString(),
                "time", TimeUtils.curTimeStr()
        );
    }

}
