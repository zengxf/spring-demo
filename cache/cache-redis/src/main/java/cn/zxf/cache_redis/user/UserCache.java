package cn.zxf.cache_redis.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Component;

import static cn.zxf.cache_redis.config.RedisCacheConfig.*;

@Slf4j
@Component
public class UserCache {

    @Cacheable(value = USER_ID_CACHE, key = "#id")
    public User findById(String id) {
        User user = User.builder()
                .id(id)
                .name("test-" + id)
                .status(1)
                .build();
        log.info("findOne by id - init-user: {}", user);
        return user;
    }

    @Cacheable(value = USER_MOBILE_CACHE, key = "#mobile")
    public User findByMobile(String mobile) {
        User user = User.builder()
                .id("id-" + mobile)
                .name("test-" + mobile)
                .mobile(mobile)
                .status(1)
                .build();
        log.info("findOne by mobile - init-user: {}", user);
        return user;
    }

    @Cacheable(value = USER_NAME_CACHE, key = "#name")
    public User findByName(String name) {
        User user = User.builder()
                .id("id-" + name)
                .name(name)
                .status(1)
                .build();
        log.info("findOne by name - init-user: {}", user);
        return user;
    }

    @Caching(put = {
            @CachePut(value = USER_ID_CACHE, key = "#user.id"),
            @CachePut(value = USER_MOBILE_CACHE, key = "#user.mobile", condition = "#user.mobile != null && #user.mobile != ''"),
            @CachePut(value = USER_NAME_CACHE, key = "#user.name", condition = "#user.name != null && #user.name != ''"),
    })
    public void save(User user) {
        log.info("save - user: {}", user);
    }

    @CacheEvict(value = USER_ID_CACHE, key = "#id")
    public void deleteById(String id) {
        log.info("delete - id: {}", id);
    }

}
