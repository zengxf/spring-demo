# Spring Boot 启动过程-研究
- 原文参考：https://juejin.im/post/5ce5effb6fb9a07f0b039a14
- 原理参考：https://www.jianshu.com/p/83693d3d0a65

## Spring Boot 启动阶段 1
- 创建 `SpringApplication` 对象
- 保存主配置类
- 判断当前是否是一个 Web 应用 
- 从 `Spring.factories` 查找所有 `ApplicationContextInitializer`，然后保存起来 
- 从 `Spring.factories` 查找所有 `ApplicationListener`，然后保存起来 
- 从多个配置类中找到有 `main` 方法的主配置类（只有一个） 
 
## Spring Boot 启动阶段 2
- 运行 `run()` 方法
- 创建计时器 
- 声明 IoC 容器 
- 从 `Spring.factories` 获取 `SpringApplicationRunListeners` 
- 回调所有 `SpringApplicationRunListeners` 的 starting() 方法 
- 封装命令行参数 
- 准备环境，创建环境后回调 `SpringApplicationRunListeners#environmentPrepared()` 方法，表示环境准备完成
- 打印 Banner 
- 创建 IoC 容器（决定创建 Web 的 IoC 容器还是普通的 IoC 容器） 
- 准备上下文环境，将 environment 保存到 IoC 容器中，并调用 `applyInitializers()` 方法
  - 回调之前保存的所有的 `ApplicationContextInitializer` 的 `initialize()` 方法
  - 回调所有的 `SpringApplicationRunListener#contextPrepared()` 方法 
  - 最后回调所有的 `SpringApplicationRunListener#contextLoaded()` 方法
- 刷新容器，IoC 容器初始化（如果是 Web 应用则会创建嵌入式的 Tomcat），扫描、创建、加载所有组件的地方
- 调用所有 `SpringApplicationRunListeners#started()`方法 
- 从 IoC 容器中获取所有的 `ApplicationRunner` 和 `CommandLineRunner` 进行回调 

## 自动配置原理
- 注解结构：
```
@SpringBootApplication
    @EnableAutoConfiguration
        @AutoConfigurationPackage
            @Import({Registrar.class})
        @Import(AutoConfigurationImportSelector.class)
```
- 说明：
  - `@Import({Registrar.class})` 注解就是将
    - 主配置类（@SpringBootConfiguration 标注的类）的所在包及子包的所有组件扫描到 Spring 容器中
  - `@Import(AutoConfigurationImportSelector.class)` 导入另外 N 个自动配置类

## 自动配置类的形式
- `XxAutoConfiguration`：自动配置类给容器中添加组件
  - `@Configuration` 标识为配置类
  - `@EnableConfigurationProperties({XxProperties.class})` 引入属性配置
  - `@Autowired XxProperties cfg;` 引入使用
  - `spring.factories` 文件中配置
- `XxProperties`：封装配置文件中相关属性
  - `@ConfigurationProperties(prefix = "spring.http")` 从配置文件中获取指定的值和 Bean 属性进行绑定 
