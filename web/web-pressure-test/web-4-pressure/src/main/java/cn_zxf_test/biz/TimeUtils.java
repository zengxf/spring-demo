package cn_zxf_test.biz;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <p/>
 * Created by ZXFeng on 2025/12/17
 */
public class TimeUtils {

    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    public static String curTimeStr() {
        return LocalDateTime.now().format(FORMATTER);
    }

}
