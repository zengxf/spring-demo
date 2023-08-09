package cn.zxf.spring_aop.spring_dump_test;

import org.springframework.stereotype.Service;

@Service
public class DemoMethodService {

    public void add() {
        System.out.println("进入 Demo-Method-Service #add() ...");
    }

    private void testPrivate() {
        System.out.println("私有不可见");
    }

    void testPackage() {
        System.out.println("包可见");
    }

    public void testError() {
        System.out.println("出错");
        throw new RuntimeException("------ 出错测试 ------");
    }

    public int testReturn() {
        System.out.println("返回结果");
        return 888888;
    }

    protected void testProtected() {
        System.out.println("子类可见");
    }

    // 不能 AOP，不进行代理
    public final void testPublicFinal() {
        System.out.println("公共 & Final");
    }

}
