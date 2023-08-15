package test.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.vladmihalcea.hibernate.type.util.ObjectMapperSupplier;

/**
 * <br/>
 * Created by ZXFeng on 2022/3/11.
 */
public class MyObjectMapperSupplier implements ObjectMapperSupplier {

    public static final ObjectMapper mapper;

    static {
        mapper = new ObjectMapper()
                .enable(SerializationFeature.INDENT_OUTPUT)
                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Override
    public ObjectMapper get() {
        return mapper;
    }

}
