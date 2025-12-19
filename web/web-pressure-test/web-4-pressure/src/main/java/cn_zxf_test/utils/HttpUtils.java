package cn_zxf_test.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * <p/>
 * Created by ZXFeng on 2025/12/19
 */
@Slf4j
public class HttpUtils {

    public static String doGet(String url) {
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(URI.create(url)).build();
        try {
            // 结合 Java 21+ 的虚拟线程，HttpClient 在底层已做优化
            HttpResponse<String> res = client.send(request, HttpResponse.BodyHandlers.ofString());
            return res.body();
        } catch (IOException | InterruptedException e) {
            log.error("出错", e);
            return "ERR: " + e.getMessage();
        }
    }

}
