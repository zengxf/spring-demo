open module cn.test {
    requires static lombok; // 需要 static

    requires java.desktop;  // NoClassDefFoundError: java/beans/PropertyEditorSupport
    // requires java.naming;   // **.merged.module does not read module java.naming (写在这没用)

    requires org.slf4j;

    requires jakarta.annotation;

    requires com.fasterxml.jackson.annotation;

    requires spring.boot;
    requires spring.boot.autoconfigure;
    // requires spring.boot.starter.web;    // 没必要
    // requires spring.boot.starter.logging;

    requires spring.core;   // 需要；否则会出现 cannot access class org.springframework.cglib.core.ReflectUtils
    requires spring.beans;
    requires spring.context;
    requires spring.web;

    requires org.apache.tomcat.embed.core;      // 二选一，或者没有 Tomcat 类
    requires org.apache.tomcat.embed.el;
    requires org.apache.tomcat.embed.websocket;

    exports cn.test;
    exports cn.test.biz;
    // opens cn.test;       // 上面已经 open 整个模块了
    // opens cn.test.biz;
}