package test.config;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import test.BaseDSerAppTest5;

/**
 * <br/>
 * Created by ZXFeng on 2023/2/26.
 */
@Slf4j
class DSerConfigTest extends BaseDSerAppTest5 {

    @Autowired
    DSerConfig config;

    @Test
    public void printConfig() {
        config.init();
    }

}