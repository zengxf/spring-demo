package cn_zxf_test.http;

import org.junit.Test;

/**
 * 常规 HTTP 请求测试
 * <p/>
 * ZXF 创建于 2025/1/23
 */
public class CommonTest extends BaseHTester {

    /*** 上游主从 */
    @Test
    public void testNginxMasterSlave() {
        String uid = "J-9968";
        String url = "http://127.0.0.1:9812/test/nginx/master-slave";
        String reqBody = "{\"key1\": \"v1-J29\", \"k2\": 88}";
        post(uid, url, reqBody);
    }

    /*** 负载均衡-最少连接 */
    @Test
    public void testNginxLbLeastConn() {
        String uid = "J-9978";
        String url = "http://127.0.0.1:9821/test/nginx/lb-least-conn";
        String reqBody = "{\"key1\": \"v1-J36\", \"k2\": 58}";
        for (int i = 1; i <= 10; i++) {
            String res = post0(uid, url, reqBody);
            out("[{}] res: [{}]", i, res);
        }
    }

    /*** 负载均衡-轮询 */
    @Test
    public void testNginxLbRoundRobin() {
        String uid = "J-9982";
        String url = "http://127.0.0.1:9822/test/nginx/lb-round-robin";
        String reqBody = "{\"key1\": \"v1-J52\", \"k2\": 58}";
        for (int i = 1; i <= 10; i++) {
            String res = post0(uid, url, reqBody);
            out("[{}] res: [{}]", i, res);
        }
    }

    /*** 负载均衡-随机 */
    @Test
    public void testNginxLbRandom() {
        String uid = "J-9985";
        String url = "http://127.0.0.1:9823/test/nginx/lb-random";
        String reqBody = "{\"key1\": \"v1-J55\", \"k2\": 55}";
        for (int i = 1; i <= 10; i++) {
            String res = post0(uid, url, reqBody);
            out("[{}] res: [{}]", i, res);
        }
    }

    /*** 负载均衡-权重 */
    @Test
    public void testNginxLbWeight() {
        String uid = "J-9986";
        String url = "http://127.0.0.1:9824/test/nginx/lb-weight";
        String reqBody = "{\"key1\": \"v1-058\", \"k2\": 18}";
        for (int i = 1; i <= 10; i++) {
            String res = post0(uid, url, reqBody);
            out("[{}] res: [{}]", i, res);
        }
    }

    /*** 负载均衡-IP哈希 */
    @Test
    public void testNginxLbIpHash() {
        String uid = "J-9988";
        String url = "http://127.0.0.1:9825/test/nginx/lb-ip-hash";
        String reqBody = "{\"key1\": \"v1-059\", \"k2\": 26}";
        for (int i = 1; i <= 10; i++) {
            String res = post0(uid, url, reqBody);
            out("[{}] res: [{}]", i, res);
        }
    }


}
