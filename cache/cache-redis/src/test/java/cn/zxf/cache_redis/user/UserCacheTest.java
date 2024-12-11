package cn.zxf.cache_redis.user;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@RunWith(SpringRunner.class)
public class UserCacheTest {

    @Autowired
    private UserCache service;

    @Test
    public void test_save() {
        String id = "zxf-001";
        User user = User.builder()
                .id(id)
                .name("zxf")
                .status(1)
                .build();
        service.save(user);
        log.info("test-save-user: {}", user);
    }

    @Test
    public void test_saveAndGet() {
        String id = "zxf-003";
        User user = User.builder()
                .id(id)
                .name("zxf")
                .status(1)
                .build();
        service.saveAndGet(user);
        log.info("test-saveAndGet-user: {}", user);
    }

    @Test
    public void test_findOne() {
        String id = "zxf-001";
        User user = service.findOne(id);
        log.info("test-findOne-user 1: {}", user);

        user = service.findOne(id);
        log.info("test-findOne-user 2: {}", user);
    }

    @Test
    public void test_findOne2() {
        String id = "zxf-002";
        User user = User.builder()
                .id(id)
                .name("zxf")
                .status(1)
                .build();
        user = service.findOne(user);
        log.info("test-findOne-user: {}", user);
    }

    @Test
    public void test_delete() {
        String id = "zxf-002";
        service.delete(id);
        log.info("test-delete-id: {}", id);
    }

    @Test
    public void test_common() {
        String id = "zxf-004";
        Integer status = 1;
        User user = service.common(id, status);
        log.info("test-common-id: {}, status: {}, result-user", id, status, user);
    }

    @Test
    public void test_cacheCondition() {
        String id = "zxf-101";
        Integer status = 1;
        User user = service.cacheCondition(id, status);
        log.info("test-cacheCondition-id: {}, status: {}, result-user", id, status, user);

        id = "zxf-102";
        status = 2;
        user = service.cacheCondition(id, status);
        log.info("test-cacheCondition-id: {}, status: {}, result-user", id, status, user);

        id = "xx-102";
        status = 1;
        user = service.cacheCondition(id, status);
        log.info("test-cacheCondition-id: {}, status: {}, result-user", id, status, user);
    }

}
