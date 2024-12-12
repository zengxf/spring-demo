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
    private UserCache userCache;


    @Test
    public void findById() {
        String id = "zxf-001";
        User user = userCache.findById(id);
        log.info("test-findOne-user 1: {}", user);

        user = userCache.findById(id);
        log.info("test-findOne-user 2: {}", user);
    }

    @Test
    public void findByMobile() {
        String mobile = "13966668888";
        User user = userCache.findByMobile(mobile);
        log.info("test-findByMobile-user 1: {}", user);

        user = userCache.findByMobile(mobile);
        log.info("test-findByMobile-user 2: {}", user);
    }

    @Test
    public void findByName() {
        String name = "ZXF-068";
        User user = userCache.findByName(name);
        log.info("test-findByName-user 1: {}", user);

        user = userCache.findByName(name);
        log.info("test-findByName-user 2: {}", user);
    }


    @Test
    public void save() {
        {
            String id = "zxf-003";
            User user = User.builder()
                    .id(id)
                    .name("ZXF-88")
                    .mobile("132-6666-8888")
                    .status(1)
                    .build();
            userCache.save(user);
            log.info("test-save-user 1: {}", user);
        }

        {
            String id = "zxf-004";
            User user = User.builder()
                    .id(id)
                    .name("ZXF-89")
                    .mobile("") // 测试缓存条件
                    .status(1)
                    .build();
            userCache.save(user);
            log.info("test-save-user 2: {}", user);
        }
    }


    @Test
    public void deleteById() {
        String id = "zxf-001";
        userCache.deleteById(id);
        log.info("test-delete-id: {}", id);
    }

}
