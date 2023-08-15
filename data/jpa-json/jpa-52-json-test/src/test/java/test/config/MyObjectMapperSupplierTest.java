package test.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Map;

/**
 * <br/>
 * Created by ZXFeng on 2022/3/11.
 */
@Slf4j
public class MyObjectMapperSupplierTest {

    @Test
    public void toJson() throws JsonProcessingException {
        String json = MyObjectMapperSupplier.mapper.writeValueAsString(Map.of("k1", "v1"));
        log.info("json: [{}]", json);
    }

}