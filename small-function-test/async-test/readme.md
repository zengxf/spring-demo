# Spring 异步测试
- 参考： https://mp.weixin.qq.com/s/KvPJMGBzOxGbH98ed9QRpw
- 可用 `@Async("otherTaskExecutor")` 指定线程池名称
- 配置线程池
```Java
@EnableAsync
@Configuration
public class AsyncConfiguration implements AsyncConfigurer {
    @Bean(name = "asyncPoolTaskExecutor")
    public ThreadPoolTaskExecutor executor() {
        ...
    }
    @Bean(name = "otherTaskExecutor")
    public ThreadPoolTaskExecutor executor() {
        ...
    }
    /*** 指定默认线程池 */
    @Override
    public Executor getAsyncExecutor() {
        return executor();
    }
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return (ex, method, params) ->
            log.error("执行出错：{}", method.getName(), ex);
    }
}
// 使用
@Async("otherTaskExecutor")
public void doTask2() {
    ...
}
```