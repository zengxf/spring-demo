package cn.zxf.cache_redis.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.Arrays;
import java.util.HashSet;

@Configuration
@EnableCaching
public class RedisCacheConfig {

    public static final String
            USER_ID_CACHE = "user:id",
            USER_NAME_CACHE = "user:name";

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Bean
    @Primary
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(stringSerializer());
        redisTemplate.setHashKeySerializer(stringSerializer());
        redisTemplate.setValueSerializer(jsonSerializer());
        return redisTemplate;
    }

    private RedisSerializer<String> stringSerializer() {
        return new StringRedisSerializer();
    }

    private RedisSerializer<Object> jsonSerializer() {
        // PolymorphicTypeValidator ptv = BasicPolymorphicTypeValidator.builder().build();
        // PolymorphicTypeValidator ptv = new DefaultBaseTypeLimitingValidator();

        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        om.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        // om.activateDefaultTyping(ptv);

        return new Jackson2JsonRedisSerializer<>(om, Object.class);
    }


    @Bean
    public CacheManager cacheManager(RedisTemplate<?, ?> redisTemplate) {
        RedisCacheManager manager = RedisCacheManager.builder(redisConnectionFactory)
                .cacheDefaults(
                        RedisCacheConfiguration.defaultCacheConfig()
                                // .usePrefix()

                )
                .initialCacheNames(new HashSet<>(Arrays.asList(
                        USER_ID_CACHE, USER_NAME_CACHE
                )))
                .build();


        //
        //     Map<String, Long> expiresMap = new HashMap<>();
        //     expiresMap.put( "user", 60L ); // 单位秒
        //     expiresMap.put( "zxf-user", 60L ); // 单位秒
        //     manager.setExpires( expiresMap );

        return manager;
    }

}
