package test.biz;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

@Slf4j
public class BizControllerTest {

    @Test
    public void mackData() {
        {
            List<String> data = BizController.mackData(1);
            for (String item : data) {
                log.info("{}", item);
            }
        }
        {
            List<String> data = BizController.mackData(2);
            for (String item : data) {
                log.info("{}", item);
            }
        }
    }

}