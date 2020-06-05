package cn.zxf.spring_aop.manual_setup.introduction;

/**
 * Created by zengxf on 2020/6/5.
 */
public class GreetingImpl implements Greeting {

    @Override
    public void sayHello(String name) {
        System.out.println("Hello:" + name);
    }
}