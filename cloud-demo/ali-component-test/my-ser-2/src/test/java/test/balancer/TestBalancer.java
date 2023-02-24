package test.balancer;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.client.RestTemplate;
import test.BaseSer2AppTest5;

/**
 * <br/>
 * Created by ZXFeng on 2023/2/24.
 */
@Slf4j
public class TestBalancer extends BaseSer2AppTest5 {

    @LoadBalanced
    @Autowired
    RestTemplate rest;

    @Test
    public void testSer1() {
        String url = "http://my-ser-1/api/biz/hello";
        for (int i = 0; i < 10; i++) {
            String res = rest.getForObject(url, String.class);
            log.info("i: [{}], res: [{}]", i, res);
        }
    }

}
