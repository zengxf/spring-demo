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


}
