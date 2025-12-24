package cn_zxf_test.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

/**
 * <p/>
 * Created by ZXFeng on 2025/12/19
 */
@Slf4j
public class HttpUtils {

    public static String doGet(String url) {
        // HttpClient client = HttpClient.newHttpClient();
        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1) // 使用 HTTP/1.1
                // .version(HttpClient.Version.HTTP_2) // 支持 HTTP/2，多路复用更高效
                .connectTimeout(Duration.ofSeconds(3))
                .build();
        HttpRequest request = HttpRequest.newBuilder(URI.create(url))
                .GET()
                .timeout(Duration.ofSeconds(2))
                // .setHeader("Connection", "keep-alive") // 不能设置 (受限头)
                .build();
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
