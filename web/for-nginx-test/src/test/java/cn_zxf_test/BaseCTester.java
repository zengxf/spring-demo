package cn_zxf_test;

import cn.hutool.core.util.StrUtil;
import org.junit.After;
import org.junit.Before;

import java.time.LocalTime;

/**
 * <p/>
 * ZXF 创建于 2025/1/23
 */
public abstract class BaseCTester {

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
        String usS = StrUtil.format("{} m {} s {} ms", mm, ss, m);

        System.out.println();
        System.out.println("ts: " + LocalTime.now());
        System.out.println("*-*-*-* unit after  *-*-*-*");
        System.out.println("Unit 用时：[" + usS + "]");
        System.out.println();
    }

}
