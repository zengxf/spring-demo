open module cn.test {
    requires static lombok; // 需要 static

    requires java.desktop;  // NoClassDefFoundError: java/beans/PropertyEditorSupport

    requires org.slf4j;

    requires jakarta.annotation;

    requires spring.boot;
    requires spring.boot.autoconfigure;

    requires spring.core;   // 需要；否则会出现 cannot access class org.springframework.cglib.core.ReflectUtils
    requires spring.beans;
    requires spring.context;
    requires spring.web;

    exports cn.test;
    exports cn.test.biz;
}