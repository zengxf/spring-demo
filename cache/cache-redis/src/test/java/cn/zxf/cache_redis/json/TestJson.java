package cn.zxf.cache_redis.json;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * <p/>
 * ZXF 创建于 2024/12/11
 */
@Slf4j
public class TestJson {

    @Test
    public void testJson() throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        PolymorphicTypeValidator ptv = om.getPolymorphicTypeValidator();
        /**
         * PropertyAccessor.ALL: 表示这将应用到所有的属性访问器。
         * Jackson 支持多种访问器来读取或写入对象的数据，包括字段（fields）、getters、setters、构造函数参数
         * 以及任何带有 @JsonAnyGetter 或 @JsonAnySetter 注解的方法。
         * 使用 ALL 意味着这里的设置将会应用于所有这些访问器类型。
         * ------------------------------------------------------------------
         * JsonAutoDetect.Visibility.ANY: 定义了可见性策略。
         * 通常情况下，Java 类的私有成员（如私有字段或方法）不会被 Jackson 自动序列化或反序列化，除非它们通过 getter/setter 方法公开。
         * 而 Visibility.ANY 设置表示无论访问修饰符是什么，Jackson 都会尝试访问并处理这些成员。
         * 这意味着即使没有对应的 getter/setter 方法，Jackson 也会序列化/反序列化类中的私有字段。
         */
        // om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.PUBLIC_ONLY); // 没有 get() 时出错
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);  // 不存在字段时，不报错
        om.setSerializationInclusion(JsonInclude.Include.NON_NULL);     // 不包含 null 字段
        /** 序列化时，会多加 @class 字段。反序列时，会做类型校验 */
        om.activateDefaultTyping(ptv, ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);

        UserDto user = new UserDto();
        user.setId("test-11");
        user.setName("zxf");

        String json = om.writeValueAsString(user);
        log.info(json);

        json = "{\"@class\":\"cn.zxf.cache_redis.json.TestJson$UserDto\", \"id\":\"b11\", \"name-b\":\"zxf\"}"; // ok
        /**
         * 下面的 json 会出错: 类型不匹配
         *  Could not resolve type id 'cn.zxf.cache_redis.user.User' as a subtype of `cn.zxf.cache_redis.json.TestJson$UserDto`: Not a subtype
         */
        // json = "{\"@class\":\"cn.zxf.cache_redis.user.User\", \"id\":\"b-11\", \"na-me\":\"zxf\"}";
        UserDto user2 = om.readValue(json, UserDto.class);
        log.info(user2.toString());
    }

    @Accessors(chain = true)
    @ToString
    static class UserDto {
        @Setter
        // @Getter
        private String id;
        @Setter
        private String name;
        private Integer status;
    }

}
