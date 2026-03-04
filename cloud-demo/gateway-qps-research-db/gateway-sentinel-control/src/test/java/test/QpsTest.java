package test;

import cn.zxf.sentinel.cluster.gateway.filter.ResourceNameUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * <p/>
 * Created by ZXFeng on 2026/2/9
 */
@Slf4j
public class QpsTest {

    RestTemplate restTemplate = new RestTemplateBuilder()
            .defaultHeader(ResourceNameUtils.KEY_TENANT, "DC")
            .errorHandler(new Ignore429ErrorHandler())
            .build();

    @Test
    public void simpleTest() {
        get("simple", "http://localhost:9641/test-web1/api/biz/mockReq1");
    }

    @Test
    public void testApiOnCluster() {
        for (int i = 1; i <= 5; i++) {
            get("res-1", "http://localhost:9641/test-web1/api/biz/mockReq1");
            get("res-2", "http://localhost:9642/test-web1/api/biz/mockReq1");
        }
    }

    @Test
    public void testApi() {
        for (int i = 1; i <= 5; i++) {
            get("res-1", "http://localhost:9641/test-web1/api/biz/mockReq1");
        }
    }

    @Test
    public void testApp() {
        for (int i = 1; i <= 11; i++) {
            get("res-1", "http://localhost:9641/test-web1/api/biz/mockReq2");
        }
    }

    @Test
    public void test1sApi() throws InterruptedException {
        for (int i = 1; i <= 5; i++) {
            get("res-1", "http://localhost:9641/test-web1/api/biz/mockReq1");
            Thread.sleep(500);
        }
    }

    @Test
    public void test1sApp() throws InterruptedException {
        for (int i = 1; i <= 5; i++) {
            get("res-1", "http://localhost:9641/test-web1/api/biz/mockReq2");
            Thread.sleep(500);
        }
    }

    @Test
    public void test1sOnCluster() throws InterruptedException {
        for (int i = 1; i <= 5; i++) {
            get("res-1", "http://localhost:9641/test-web1/api/biz/mockReq1");
            get("res-2", "http://localhost:9642/test-web1/api/biz/mockReq1");
            Thread.sleep(1000);
        }
    }

    private void get(String sign, String url) {
        try {
            String res = restTemplate.getForObject(url, String.class);
            log.info("{}: [{}]", sign, res);
        } catch (Exception e) {
            log.error("{} err: [{}]", sign, e.getMessage());
        }
    }

    public static class Ignore429ErrorHandler extends DefaultResponseErrorHandler {
        @Override
        public boolean hasError(ClientHttpResponse response) throws IOException {
            // 如果状态码是 429，返回 false (表示不是错误，不抛异常)
            if (response.getStatusCode() == HttpStatus.TOO_MANY_REQUESTS) {
                return false;
            }
            // 其他错误（如 404, 500）依然走默认逻辑抛出异常
            return super.hasError(response);
        }
    }

}