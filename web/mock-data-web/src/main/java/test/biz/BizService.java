package test.biz;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import test.user.UserDto;
import test.utils.UserUtils;

import java.util.List;

@Slf4j
@Component
public class BizService {

    @PostConstruct
    public void init() {
        log.info("== BizService init! ==");
    }

    public void hello() {
        log.info("== hello ==");
    }

    public List<UserDto> getUsers(int size) {
        return this.getUsers(size, "id-");
    }

    public List<UserDto> getUsers(int size, String idPrefix) {
        if (size < 0)
            throw new RuntimeException("参数错误！size = " + size);
        if (size > 5)
            throw new RuntimeException("size 不能超过 5！size = " + size);
        return UserUtils.getUsers(size, idPrefix);
    }

    public void sleepMs(int ms) {
        try {
            log.info("start ----");
            long start = System.currentTimeMillis();
            Thread.sleep(ms);
            int ms2 = this.sleep5Ms();
            long useMs = System.currentTimeMillis() - start;
            log.info("end   ----");
            log.info("use ms: [{}], ms2: [{}]", useMs, ms2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    int sleep5Ms() throws InterruptedException {
        int ms = 5;
        Thread.sleep(ms);
        return ms;
    }

    // int sleep6Ms() throws InterruptedException {
    //     int ms = 6;
    //     Thread.sleep(ms);
    //     return ms;
    // }

}
