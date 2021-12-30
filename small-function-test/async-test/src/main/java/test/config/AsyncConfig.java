package test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <br/>
 * Created by ZXFeng on 2021/12/30.
 */
@EnableAsync
@Configuration
public class AsyncConfig {

    // -----------

    @Bean(name = "asyncPoolTaskExecutor1")
    public ThreadPoolTaskExecutor executor1() {
        return buildThreadPool("my-pool-11-");
    }

    @Bean(name = "asyncPoolTaskExecutor2")
    public ThreadPoolTaskExecutor executor2() {
        return buildThreadPool("my-pool-22-");
    }

    @Bean(name = "defPool")
    public ThreadPoolTaskExecutor defPool() {
        return buildThreadPool("def-pool-");
    }

    // -----------

    // 构建线程池
    private static ThreadPoolTaskExecutor buildThreadPool(
            String prefix
    ) {
        AtomicInteger poolNumber = new AtomicInteger(1);
        ThreadFactory threadFactory = (r) -> {
            String name = prefix + poolNumber.getAndIncrement();
            boolean daemon = false;
            int priority = Thread.NORM_PRIORITY;
            Thread thread = new Thread(r, name);
            thread.setDaemon(daemon);
            thread.setPriority(priority);
            return thread;
        };
        RejectedExecutionHandler rejectedHandler = (task, executor) -> {
            throw new RejectedExecutionException("对队已满！");
        };
        int coreSize = 2;
        int maxSize = 4;
        int keepAliveSeconds = 10;
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(coreSize);
        executor.setMaxPoolSize(maxSize);
        executor.setKeepAliveSeconds(keepAliveSeconds);
        executor.setQueueCapacity(10);
        executor.setThreadFactory(threadFactory);
        executor.setRejectedExecutionHandler(rejectedHandler);
        return executor;
    }

}
