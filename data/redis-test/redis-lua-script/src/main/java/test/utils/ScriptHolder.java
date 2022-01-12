package test.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.util.ObjectUtils;

/**
 * <br/>
 * Created by ZXFeng on 2022/1/12.
 */
@Slf4j
public class ScriptHolder {

    // lua 脚本的类路径
    private static String rateLimitLua = "script/rate_limiter.lua";
    private static RedisScript<Long> rateLimiterScript = null;

    public static synchronized RedisScript<Long> getRateLimitScript() {
        if (null == rateLimiterScript) {
            // 从类路径文件中，加载 lua 脚本
            String script = IOUtil.loadJarFile(ScriptHolder.class.getClassLoader(), rateLimitLua);
            if (ObjectUtils.isEmpty(script)) {
                log.error("lua script load failed:" + rateLimitLua);
            } else {
                // 创建 lua 脚本实例
                rateLimiterScript = new DefaultRedisScript<>(script, Long.class);
            }
        }
        return rateLimiterScript;
    }

}
