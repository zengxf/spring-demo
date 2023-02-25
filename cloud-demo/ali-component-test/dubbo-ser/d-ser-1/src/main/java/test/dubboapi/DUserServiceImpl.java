package test.dubboapi;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Component;
import test.dubbo.api.DUserDTO;
import test.dubbo.api.DUserService;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * <br/>
 * Created by ZXFeng on 2023/2/25.
 */
@DubboService(timeout = 3000)
public class DUserServiceImpl implements DUserService {

    @Override
    public DUserDTO getUser(String id) {
        return new DUserDTO()
                .setName("id-" + id).setAge(66)
                .setSign("OK-" + LocalTime.now().format(DateTimeFormatter.ofPattern("HH-mm-ss")));
    }

    @Override
    public List<DUserDTO> getUsers(Integer pageSize) {
        int max = Math.max(1, pageSize);
        return IntStream.rangeClosed(1, max)
                .mapToObj(i -> {
                    return new DUserDTO()
                            .setName("uid-" + i).setAge(10 * i)
                            .setSign("OK-" + LocalTime.now().format(DateTimeFormatter.ofPattern("HH-mm-ss")));
                })
                .collect(Collectors.toList());
    }

}
