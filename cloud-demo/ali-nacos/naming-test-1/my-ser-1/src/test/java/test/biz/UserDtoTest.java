package test.biz;

import common.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * <br/>
 * Created by ZXFeng on 2024/1/3.
 */
@Slf4j
public class UserDtoTest {

    @Test
    public void test() {
        log.info("user: {}", UserDto.of("zxf", 33));
    }

}
