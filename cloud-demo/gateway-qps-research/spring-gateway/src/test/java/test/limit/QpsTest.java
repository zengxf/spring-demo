package test.limit;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

/**
 * <p/>
 * Created by ZXFeng on 2026/1/28
 */
@Slf4j
public class QpsTest {

    @Test
    public void test() {
        RestTemplate restTemplate = new RestTemplate();
        for (int i = 0; i < 5; i++) {
            try {
                String str = restTemplate.getForObject("http://localhost:9661/test-web1/api/biz/mockReq1", String.class);
                log.info("res: [{}]", str);
            } catch (Exception e) {
                log.error("err: [{}]", e.getMessage());
            }
        }
    }

}
