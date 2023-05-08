package test.biz;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import test.user.UserDto;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Component
public class BizService {

    @PostConstruct
    public void init() {
        log.info("== BizService init! ==");
    }

    public void hello() {
        log.info("== hello ==");
    }

    public List<UserDto> getUsers(int size) {
        return this.getUsers(size, "id-");
    }

    public List<UserDto> getUsers(int size, String idPrefix) {
        if (size < 0)
            throw new RuntimeException("参数错误！size = " + size);
        if (size > 5)
            throw new RuntimeException("size 不能超过 5！size = " + size);

        return IntStream.rangeClosed(1, size)
                .mapToObj(i -> new UserDto()
                        .setId(idPrefix + i)
                        .setName("test-" + i)
                        .setAge(32 + i)
                )
                .collect(Collectors.toList());
    }

}
