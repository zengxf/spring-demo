package cn.zxf.spring_aop.spring_dump_test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemoAnnotationService {

    @Autowired
    DemoAnnotationService self;

    @Action(name = "注解式拦截的 add 操作")
    public void add() {
        System.out.println("进入 Demo-Annotation-Service #add() ...");
        System.out.println("this.add1()");
        this.add1();
        System.out.println("self.add1()");
        self.add1();
    }

    @Action(name = "注解式拦截的 add1 操作")
    public void add1() {
        System.out.println("进入 Demo-Annotation-Service #add1() ...");
    }

}
