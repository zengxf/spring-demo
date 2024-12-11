package cn.zxf.cache_redis.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserService {

    static final String key_1 = "'zxf-user:' + #user.id";

    @CachePut(value = "zxf-user", key = "'zxf-user:' + #user.id")
    public void save(User user) {
        log.info("save - user: {}", user);
    }

    @CachePut(value = "zxf-user", key = key_1)
    public User saveAndGet(User user) {
        user.setStatus(2);
        log.info("saveAndGet - user: {}", user);
        return user;
    }

    @Cacheable(value = "user", key = "#id")
    public User findOne(String id) {
        User user = User.builder()
                .id(id)
                .name("test-" + id)
                .status(1)
                .build();
        log.info("findOne - user: {}", user);
        return user;
    }

    @Cacheable(value = "user", key = "#user.id")
    public User findOne(User user) {
        user.setName("test-" + user.getId());
        log.info("findOne - user: {}", user);
        return user;
    }

    @CacheEvict(value = "user", key = "#id")
    public void delete(String id) {
        log.info("delete - id: {}", id);
    }

    @Caching(cacheable = @Cacheable(value = "user", key = "#id"))
    public User common(String id, Integer status) {
        User user = User.builder()
                .id(id)
                .name("test-" + id)
                .status(status)
                .build();
        log.info("common - user: {}", user);
        return user;
    }

    @Cacheable(value = "user", key = "'zxf:' + #id", unless = "#result == null || #result.status == 1 || #result.id.startsWith( 'xx-' )")
    public User cacheCondition(String id, Integer status) {
        User user = User.builder()
                .id(id)
                .name("test-" + id)
                .status(status)
                .build();
        log.info("cacheCondition - user: {}", user);
        return user;
    }

}
