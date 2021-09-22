package test.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import test.user.UserDto;

/**
 * <br/>
 * Created by ZXFeng on  2021/9/22.
 */
@Slf4j
public class JsonUtils {

    public static String toJson(Object obj) throws JsonProcessingException {
        if (obj == null)
            return "";
        ObjectMapper om = new ObjectMapper()
                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        String json = om.writeValueAsString(obj);
        return json;
    }

    @Test
    public void testToJson() throws JsonProcessingException {
        UserDto user = new UserDto()
                .setName("test-11")
                .setAge(32)
                .setRemark("单元测试-创建");
        String json = toJson(user);
        log.info("json: [ {} ]", json);
    }

}
