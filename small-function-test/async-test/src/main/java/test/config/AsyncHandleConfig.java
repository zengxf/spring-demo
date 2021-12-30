package test.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;

/**
 * <br/>
 * Created by ZXFeng on 2021/12/30.
 */
@Component
@Slf4j
public class AsyncHandleConfig implements AsyncConfigurer {

    @Autowired
    @Qualifier("defPool")
    ThreadPoolTaskExecutor executor;


    /*** 指定默认线程池 */
    @Override
    public Executor getAsyncExecutor() {
        return this.executor; // 返回不为 null，就用当前的
        // return null; // 返回 null，Spring 自己创建一个
    }

    /*** 指定异常处理 */
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return (ex, method, params) ->
                log.error("\n-----> 执行出错：method: [{}]", method.getName(), ex);
    }

}
