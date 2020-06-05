# AOP 原理

## 导出类经验

### 导出代理类经验
- CGLIB 导出 `System.setProperty( DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, savePath )`
- JDK 导出：
- - Java8：`System.setProperty( "sun.misc.ProxyGenerator.saveGeneratedFiles", "true" );`
- - Java9：`System.setProperty( "jdk.proxy.ProxyGenerator.saveGeneratedFiles", "true" );`
- - 生成在项目根目录下的 `com\sun\proxy` 文件下

### 导出引用的 class
```
	Path savePath = saveFolder.resolve( clazz.getSimpleName() + ".class" );
	String classPath = "/" + clazz.getName().replace( ".", "/" ) + ".class";
	InputStream is = clazz.getResourceAsStream( classPath );
	Files.copy( is, savePath );
```

## 术语
- [原文参考 jianshu](https://www.jianshu.com/p/601713b20e43)
- **连接点（JoinPoint）**
  - 要通知的特定位置
  - **通知**与**目标方法**之间的连接
  - Spring 仅支持方法的**连接点**，即仅能在方法调用前、调用后、抛异常时
- **切点（Pointcut）**
  - 定出规则，查找连接点
  - `@Pointcut`
  - **连接点**相当于数据库中的记录，而**切点**相当于查询条件
- **通知（Advice）**
  - 也叫**增强**，加入额外的功能（一段程序代码）
  - `@Around` `@Before` `@After` `@AfterReturning` `@AfterThrowing`
  - 正常-顺序：`@Around-1` >> `@Before` >> `user-code` >> `@Around-2` >> `@After` >> `@AfterReturning` 
  - 异常-顺序：`@Around-1` >> `@Before` >> `user-code` >> `@After` >> `@AfterThrowing`
- **切面（Aspect）**
  - 由**通知**和**切点**组成
  - 既包括了**横切逻辑（通知）**的定义，也包括了**连接点**的定义
  - `@Aspect` 直接在 Class 上
- **目标（Target）**
  - 被切入的类
  - `@CustomAnnotation` 自定义的注解
- **代理(Proxy)**
  - 生成的代理类，对目标类进行封装
  - 可能是和原类具有相同接口的类，也可能就是原类的子类
- **织入（Weaving）**
  - 将**通知**添加到目标类具体**连接点**上的过程
  - 生成代理类
- **引入（Introduction）**
  - 为类添加一些属性和方法
  - 如：动态添加接口实现
- 在 AOP 中，对**方法**的增强，称之为**织入**（Weaving）
  - 对**类**的增强，称之为**引入**（Introduction）

## 参考
### Spring AOP
- [参考 ibm](https://www.ibm.com/developerworks/cn/java/j-lo-springaopcglib)
- Spring 允许使用 AspectJ Annotation 用于定义方面（Aspect）、切入点（Pointcut）和增强处理（Advice）
- Spring 只是使用了和 AspectJ 5 一样的注解，但并没有使用 AspectJ 的编译器或者织入器（Weaver）
- 底层依然使用的是 Spring AOP，依然是在运行时动态生成 AOP 代理

#### 原理
- [原文参考 jianshu](https://www.jianshu.com/p/fa37e1db31af)
- 使用哪种方式创建代理类：可查看 `DefaultAopProxyFactory`
- `CglibAopProxy` 设置 `Callback[]`
- `DynamicAdvisedInterceptor#intercept()` 进行循环调用拦截器，最后调用方法

### manual_setup
- [**JDK 代理** 参考 jianshu](https://www.jianshu.com/p/df6bd74db9c5)
- [**实现引入** 参考 csdn](https://blog.csdn.net/zyhlwzy/article/details/70314157)