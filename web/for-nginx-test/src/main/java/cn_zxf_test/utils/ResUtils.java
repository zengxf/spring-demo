package cn_zxf_test.utils;

/**
 * <p/>
 * ZXF 创建于 2025/2/5
 */
public class ResUtils {

    public static String get1k() {
        StringBuilder res = new StringBuilder();

        int c1K = 1024;
        String str = "abc1234567890-";

        int n = c1K / str.length();
        int b = c1K % str.length();
        for (int i = 0; i < n; i++) {
            res.append(str);
        }
        if (b > 0) {
            res.append(str.substring(0, b));
        }

        return res.toString();
    }

}
