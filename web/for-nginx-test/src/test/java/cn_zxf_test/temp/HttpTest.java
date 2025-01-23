package cn_zxf_test.temp;

import cn_zxf_test.BaseCTester;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * <p/>
 * ZXF 创建于 2025/1/23
 */
public class HttpTest extends BaseCTester {

    @Test
    public void testMasterSlave() {
        String url = "http://127.0.0.1:9812/test/nginx/master-slave";
        String uid = "J-9968";
        String reqBody = "{\"key1\": \"v1-J29\", \"k2\": 88}";
        post(url, uid, reqBody);
    }


    // ----------------------------------------

    /**
     * POST 请求
     *
     * @param url     目标 URL
     * @param uid     请求标识
     * @param reqBody 请求体 (GET 不会发送，POST 才会发送)
     */
    private static void post(String url, String uid, String reqBody) {
        // 创建 RestTemplate 实例
        RestTemplate restTemplate = new RestTemplate();

        // 设置请求头
        HttpHeaders reqHeaders = new HttpHeaders();
        reqHeaders.set("Content-Type", "application/json");
        reqHeaders.set("X-UID", uid);

        // 构建 HttpEntity 对象
        HttpEntity<String> reqMain = new HttpEntity<>(reqBody, reqHeaders);

        // 发送 POST 请求并接收响应
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, reqMain, String.class);

        // 打印响应结果
        System.out.println(response.getBody());
    }

}
