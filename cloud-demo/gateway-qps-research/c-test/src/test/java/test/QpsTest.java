package test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

/**
 * <p/>
 * Created by ZXFeng on 2026/2/9
 */
@Slf4j
public class QpsTest {

    RestTemplate restTemplate = new RestTemplate();

    @Test
    public void test() {
        for (int i = 1; i <= 5; i++) {
            // log.info("================ {} ================", i);
            // String str = restTemplate.getForObject("http://localhost:9661/test-web1/api/biz/mockReq1", String.class);

            get("res-1", "http://localhost:9641/test-web1/api/biz/mockReq1");
            get("res-2", "http://localhost:9642/test-web1/api/biz/mockReq1");
        }
    }

    @Test
    public void test1s() throws InterruptedException {
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

}