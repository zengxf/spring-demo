# 测试-参数-校验
- 另一示例可参考 `web-error-handler`
- 依赖项 `spring-boot-starter-validation`
- 异常处理要监听 `MethodArgumentNotValidException`

## 自定义验证注解
- 要用 `@Constraint` 指定验证器
- 同时要有 `groups` 和 `payload` 参数
  - `Class<?>[] groups() default {};`
  - `Class<? extends Payload>[] payload() default {};`

## 自定义验证器
- 要实现 `ConstraintValidator` 接口

## 校验模式
- 默认会校验所有属性，然后将错误信息一起返回
- 参考 `ValidationConfiguration` 类
  - 一个校验失败，其它就不必校验
  - 错误信息也只返回一条

## @Validated 和 @Valid 区别
- 分组：提供 / 不提供
- 注解地方——用在成员属性（字段）上：不能 / 能
- 嵌套验证：不支持 / 支持

## 参考
- [快速入手 Spring Boot 参数校验](http://www.cnblogs.com/cjsblog/p/8946768.html)
- [@Validated 和 @Valid 区别](https://blog.csdn.net/qq_27680317/article/details/79970590)