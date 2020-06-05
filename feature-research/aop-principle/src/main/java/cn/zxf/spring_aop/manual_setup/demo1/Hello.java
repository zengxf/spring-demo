package cn.zxf.spring_aop.manual_setup.demo1;

public class Hello implements IHello {

    @Override
    public void say() {
        System.out.println( "hello word" );
    }
}
