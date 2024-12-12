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
    public void findById() {
        String id = "zxf-001";
        User user = service.findById(id);
        log.info("test-findOne-user 1: {}", user);

        user = service.findById(id);
        log.info("test-findOne-user 2: {}", user);
    }

    @Test
    public void findByMobile() {
        String mobile = "13966668888";
        User user = service.findByMobile(mobile);
        log.info("test-findByMobile-user 1: {}", user);

        user = service.findByMobile(mobile);
        log.info("test-findByMobile-user 2: {}", user);
    }

    @Test
    public void findByName() {
        String name = "ZXF-068";
        User user = service.findByName(name);
        log.info("test-findByName-user 1: {}", user);

        user = service.findByName(name);
        log.info("test-findByName-user 2: {}", user);
    }


    @Test
    public void save() {
        String id = "zxf-003";
        User user = User.builder()
                .id(id)
                .name("ZXF-88")
                .mobile("132-6666-8888")
                .status(1)
                .build();
        service.save(user);
        log.info("test-save-user: {}", user);
    }


    @Test
    public void deleteById() {
        String id = "zxf-001";
        service.deleteById(id);
        log.info("test-delete-id: {}", id);
    }

}
