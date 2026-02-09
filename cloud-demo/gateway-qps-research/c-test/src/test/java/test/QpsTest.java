package test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

/**
 * {@link com.alibaba.csp.sentinel.cluster.client.DefaultClusterTokenClient}
 * <p/>
 * Created by ZXFeng on 2026/2/9
 */
@Slf4j
public class QpsTest {

    @Test
    public void test() {
        RestTemplate restTemplate = new RestTemplate();
        for (int i = 1; i <= 5; i++) {
            // log.info("================ {} ================", i);
            try {
                // String str = restTemplate.getForObject("http://localhost:9661/test-web1/api/biz/mockReq1", String.class);

                String str1 = restTemplate.getForObject("http://localhost:9641/test-web1/api/biz/mockReq1", String.class);
                log.info("res-1: [{}]", str1);

                String str2 = restTemplate.getForObject("http://localhost:9642/test-web1/api/biz/mockReq1", String.class);
                log.info("res-2: [{}]", str2);
            } catch (Exception e) {
                log.error("err: [{}]", e.getMessage());
            }
        }
    }

}