package cn.zxf.cache_redis.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.DefaultBaseTypeLimitingValidator;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashSet;

@Configuration
@EnableCaching
public class RedisCacheConfig {

    public static final String
            USER_ID_CACHE = "user:id",
            USER_MOBILE_CACHE = "user:mobile",
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
        redisTemplate.setValueSerializer(this.jsonSerializer());
        return redisTemplate;
    }

    private RedisSerializer<String> stringSerializer() {
        return new StringRedisSerializer();
    }

    private RedisSerializer<Object> jsonSerializer() {
        ObjectMapper om = new ObjectMapper();
        PolymorphicTypeValidator ptv = om.getPolymorphicTypeValidator();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        om.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        // 此项必须配置，否则会报 java.lang.ClassCastException: java.util.LinkedHashMap cannot be cast to XXX
        om.activateDefaultTyping(ptv, ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);

        return new Jackson2JsonRedisSerializer<>(om, Object.class);
    }


    @Bean
    public CacheManager cacheManager() {
        RedisCacheManager manager = RedisCacheManager.builder(redisConnectionFactory)
                .cacheDefaults(
                        RedisCacheConfiguration.defaultCacheConfig()
                                .entryTtl(Duration.ofMinutes(30))
                                // .disableKeyPrefix() // def: true
                                .serializeValuesWith(SerializationPair.fromSerializer(this.jsonSerializer()))
                )
                .withCacheConfiguration(USER_MOBILE_CACHE,
                        RedisCacheConfiguration.defaultCacheConfig()
                                .serializeValuesWith(SerializationPair.fromSerializer(this.jsonSerializer()))
                                .entryTtl(Duration.ofMinutes(10))
                )
                .initialCacheNames(new HashSet<>(Arrays.asList( // 用默认的配置
                        USER_ID_CACHE, USER_NAME_CACHE
                )))
                .build();
        return manager;
    }

}
