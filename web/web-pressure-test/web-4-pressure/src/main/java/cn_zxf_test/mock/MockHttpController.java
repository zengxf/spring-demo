package cn_zxf_test.mock;

import cn.hutool.core.util.StrUtil;
import cn_zxf_test.biz.TimeUtils;
import cn_zxf_test.utils.HttpUtils;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class MockHttpController {
    String BAIDU_BASE_URL = "https://www.baidu.com";

    // http://localhost:9001/mock/http/baidu
    @GetMapping("baidu")
    public Map<String, Object> baidu() {
        long start = System.currentTimeMillis();

        String v = String.format("%s.%s", System.currentTimeMillis(), System.nanoTime());
        String url = String.format("%s?v=%s", BAIDU_BASE_URL, v);
        String result = HttpUtils.doGet(url);

        long useTime = System.currentTimeMillis() - start;
        this.printBaidu(useTime, result);   // 结果都是正常的

        return Map.of(
                "v", v,
                "useTime", useTime,
                "result_len", StrUtil.length(result),
                "thread", Thread.currentThread().toString(),
                "time", TimeUtils.curTimeStr()
        );
    }

    // 百度基本上在 40ms 左右
    private void printBaidu(long useTime, String result) {
        log.info("Baidu -> useTime: {}, res-len: {}, result: {}",
                useTime, StrUtil.length(result), StrUtil.sub(result, 0, 50)
        );
    }

}
