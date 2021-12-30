package test.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Component;
import org.springframework.util.ErrorHandler;

import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <br/>
 * Created by ZXFeng on 2021/12/30.
 */
@Component
@Slf4j
public class ScheduleHandleConfig implements SchedulingConfigurer {

    @Override
    public void configureTasks(ScheduledTaskRegistrar registrar) {
        ThreadPoolTaskScheduler taskScheduler = buildThreadPool("my-schedule-");
        taskScheduler.initialize();
        registrar.setTaskScheduler(taskScheduler);
    }

    // -----------

    // 构建线程池
    private static ThreadPoolTaskScheduler buildThreadPool(
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
        int coreSize = 4;
        ErrorHandler errorHandler = (ex) -> log.error("\n-----> 调度执行出错！", ex);
        ThreadPoolTaskScheduler executor = new ThreadPoolTaskScheduler();
        executor.setPoolSize(coreSize);
        executor.setThreadFactory(threadFactory);
        executor.setRejectedExecutionHandler(rejectedHandler);
        executor.setErrorHandler(errorHandler);
        return executor;
    }

}
