package cn_zxf_test.http;

import cn.hutool.core.util.StrUtil;
import org.junit.After;
import org.junit.Before;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.LocalTime;

/**
 * HTTP 单测父类
 * <p/>
 * ZXF 创建于 2025/1/23
 */
public abstract class BaseHTester {

    static ThreadLocal<Long> startUnitTs = new ThreadLocal<>();

    @Before
    public void before() {
        startUnitTs.set(System.currentTimeMillis());
        System.out.println();
        System.out.println("*-*-*-* unit before *-*-*-*");
        System.out.println("ts: " + LocalTime.now());
        System.out.println();
    }

    @After
    public void after() {
        long us = System.currentTimeMillis() - startUnitTs.get();
        long mm = us / 60_000L;
        long ss = us / 1000L % 60L;
        long m = us % 1000L;
        String str = StrUtil.format("{} m {} s {} ms", mm, ss, m);

        System.out.println();
        System.out.println("ts: " + LocalTime.now());
        System.out.println("*-*-*-* unit after  *-*-*-*");
        System.out.println("单测用时：[" + str + "]");
        System.out.println();
    }


    // ----------------------------------------

    /*** 日志打印 */
    protected static void out(String msg, Object... args) {
        System.out.println(StrUtil.format(msg, args));
    }


    // ----------------------------------------

    /**
     * POST 请求 (不返回结果，直接输出)
     */
    protected static void post(String uid, String url, String reqBody) {
        // 打印响应结果
        System.out.println(post0(uid, url, reqBody));
    }

    /**
     * POST 请求 (返回结果)
     *
     * @param uid     请求标识
     * @param url     目标 URL
     * @param reqBody 请求体 (GET 不会发送，POST 才会发送)
     */
    protected static String post0(String uid, String url, String reqBody) {
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

        // 返回响应结果
        return response.getBody();
    }

}
