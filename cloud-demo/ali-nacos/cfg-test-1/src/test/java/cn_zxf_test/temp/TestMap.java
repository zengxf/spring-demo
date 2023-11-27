package cn_zxf_test.temp;

import org.junit.Test;

import java.util.concurrent.ConcurrentHashMap;

/**
 * <p/>
 * 曾献峰(656553) 创建于 2023/11/27
 */
public class TestMap {

    @Test
    public void test() {
        ConcurrentHashMap<String, Integer> CACHE = new ConcurrentHashMap<>();
        String key = "k1";

        Integer v1 = CACHE.putIfAbsent(key, 1);
        System.out.println(v1);

        Integer v2 = CACHE.putIfAbsent(key, 2);
        System.out.println(v2);

        Integer v3 = CACHE.putIfAbsent(key, 3);
        System.out.println(v3);
    }

}
