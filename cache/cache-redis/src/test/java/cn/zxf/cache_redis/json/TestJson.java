package cn.zxf.cache_redis.json;

import cn.zxf.cache_redis.user.User;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.StreamReadFeature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
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
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        om.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        // om.activateDefaultTyping(ptv, ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY); // 加 @class

        User user = new User();
        user.setId("test-11");
        user.setName("zxf");

        String json = om.writeValueAsString(user);
        log.info(json);

        json = "{\"id\":\"test-11\",\"name-b\":\"zxf\"}";
        User user2 = om.readValue(json, User.class);
        log.info(user2.toString());
    }

}
