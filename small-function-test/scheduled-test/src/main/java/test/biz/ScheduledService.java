package test.biz;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * <br/>
 * Created by ZXFeng on 2021/12/30.
 */
@Component
@Slf4j
public class ScheduledService {

    private static final DateTimeFormatter
            timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Scheduled(fixedRate = 5000L)
    public void fixedRate() {
        log.info("------- fixedRate time: [{}]", LocalTime.now().format(timeFormat));
    }

    @Scheduled(cron = "*/5 * * * * ?")
    public void cron() {
        log.info("------- cron time: [{}]", LocalTime.now().format(timeFormat));
    }

    @Scheduled(fixedRate = 8000L)
    public void fixedRateError() {
        log.info("------- fixedRateError time: [{}]", LocalTime.now().format(timeFormat));
        throw new RuntimeException("出错测试！");
    }

}
