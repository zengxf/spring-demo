package cn_zxf_test.temp;

import cn_zxf_test.utils.ResUtils;
import org.junit.Test;

/**
 * <p/>
 * ZXF 创建于 2025/1/21
 */
public class TempTest {

    @Test
    public void testStrLen() {
        String str = "{\"key1\": \"v1-009\", \"k2\": 88}";
        System.out.println("len: " + str.length());
    }

    @Test
    public void ResUtilsGet1k() {
        for (int i = 0; i < 10; i++) {
            String res = ResUtils.get1k();
            System.out.println(res);
        }
        // ---------------------
        String res = ResUtils.get1k();
        System.out.println(res);
        System.out.println(res.length());
    }

}
