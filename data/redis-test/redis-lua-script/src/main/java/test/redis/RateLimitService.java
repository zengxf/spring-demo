package test.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;
import test.utils.ScriptHolder;

import java.util.List;

/**
 * <br/>
 * Created by ZXFeng on 2022/1/12.
 */
@Slf4j
@Component
public class RateLimitService {

    public static final String
            RATE_LIMITER_KEY_PREFIX = "test_limiter:",
            RATE_LIMITER_LUA_SHA_1 = RATE_LIMITER_KEY_PREFIX + "sha1";
    public static RedisScript<Long>
            rateLimiterScript = null;

    @Autowired
    private RedisTemplate redisTemplate;
    private LimiterInfo limiterInfo;

    public RateLimitService() {
        rateLimiterScript = ScriptHolder.getRateLimitScript();
    }

    public Boolean tryAcquire() {
        Long acquire = (Long) redisTemplate.execute(
                rateLimiterScript,
                List.of(limiterInfo.fullKey()),
                "acquire",
                "1");
        if (acquire == null) {
            log.warn("acquire 结果为 null");
            return false;
        }
        return acquire == 1;
    }

    /**
     * 创建一个限流的 key
     */
    public void initLimitKey(LimiterInfo limiterInfo) {
        String sha1 = rateLimiterScript.getSha1();
        log.info("----> lua-script-sha1: [{}]", sha1);
        redisTemplate.opsForValue().set(RATE_LIMITER_LUA_SHA_1, sha1);

        String maxPermits = limiterInfo.getMaxPermits().toString();
        String rate = limiterInfo.getRate().toString();
        Long result = (Long) redisTemplate.execute(
                rateLimiterScript,
                List.of(limiterInfo.fullKey()),
                "init",
                maxPermits,
                rate);
        sha1 = rateLimiterScript.getSha1();
        log.info("init-res: [{}], lua-script-sha1: [{}]", result, sha1);

        this.limiterInfo = limiterInfo;
    }

}
