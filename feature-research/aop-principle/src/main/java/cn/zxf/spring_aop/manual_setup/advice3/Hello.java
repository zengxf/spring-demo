package cn.zxf.spring_aop.manual_setup.advice3;

public class Hello implements IHello {

    @Override
    public void say() {
        System.out.println( "hello word" );
    }
}
