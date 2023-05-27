package test.bizconfig;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

@Slf4j
public class BizConfigUtilsTest {

    @Test
    public void readContent() throws URISyntaxException, IOException {
        String content = BizConfigUtils.readContent();
        log.info("content: \n\n{}\n", content);
    }

    @Test
    public void readContentUseSpring() throws URISyntaxException, IOException {
        String content = BizConfigUtils.readContentUseSpring();
        log.info("content: \n\n{}\n", content);
    }

}