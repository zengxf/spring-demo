package cn.zxf.spring_aop.manual_setup.regex;

/**
 * Created by zengxf on 2020/6/5.
 */
public class GreetingImpl implements Greeting {

    @Override
    public void sayHello(String name) {
        System.out.println("Hello:" + name);
    }

    @Override
    public void goodMonrning(String name) {
        System.out.println("Good Monrning:" + name);
    }

    @Override
    public void goodNight(String name) {
        System.out.println("Good Night:" + name);
    }
}