package test.utils;

import test.user.UserDto;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * <br>
 * Created by ZXFeng on 2023/5/8
 */
public class UserUtils {

    public static List<UserDto> getUsers(int size, String idPrefix) {
        if (size < 0)
            throw new RuntimeException("参数错误！size = " + size);
        return IntStream.rangeClosed(1, size)
                .mapToObj(i -> new UserDto()
                        .setId(idPrefix + i)
                        .setName("test-" + i)
                        .setAge(32 + i)
                        .setRemark("ts-" + LocalTime.now())
                )
                .collect(Collectors.toList());
    }

}
