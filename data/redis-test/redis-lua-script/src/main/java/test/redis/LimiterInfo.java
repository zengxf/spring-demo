package test.redis;

import lombok.Builder;
import lombok.Data;

import static test.redis.RateLimitService.RATE_LIMITER_KEY_PREFIX;

/**
 * <br/>
 * Created by ZXFeng on 2022/1/12.
 */
@Data
@Builder
public class LimiterInfo {

    /**
     * 限流器的key，如：秒杀的id
     */
    private String key;

    /**
     * 限流器的类型
     */
    private String type;

    /**
     * 限流器的最大桶容量
     */
    private Integer maxPermits;

    /**
     * 限流器的速率
     */
    private Integer rate;

    /**
     * 限流器的redis key
     */
    public String fullKey() {
        return RATE_LIMITER_KEY_PREFIX + type + ":" + key;
    }

}
