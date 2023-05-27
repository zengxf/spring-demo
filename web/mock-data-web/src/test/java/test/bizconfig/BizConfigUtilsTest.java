package test.bizconfig;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;

@Slf4j
public class BizConfigUtilsTest {

    @Test
    public void readContent() {
        String content = BizConfigUtils.readContent();
        log.info("content: \n\n{}\n", content);
    }

    @Test
    public void readContentUseSpring() throws IOException {
        String content = BizConfigUtils.readContentUseSpring();
        log.info("content: \n\n{}\n", content);
    }

}