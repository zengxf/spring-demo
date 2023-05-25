package test.temp;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * <br>
 * Created by ZXFeng on 2023/5/25
 */
@Slf4j
public class TempTest {

    @Test
    public void test() {
        log.info("String - String: {}", String.class.isAssignableFrom(String.class));
        log.info("String - CharSequence: {}", String.class.isAssignableFrom(CharSequence.class));
        log.info("CharSequence - CharSequence: {}", CharSequence.class.isAssignableFrom(CharSequence.class));
        log.info("CharSequence - String: {}", CharSequence.class.isAssignableFrom(String.class));
    }

}
