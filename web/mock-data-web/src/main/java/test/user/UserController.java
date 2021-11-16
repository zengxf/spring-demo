package test.user;

import cn.hutool.core.map.MapBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * <br/>
 * Created by ZXFeng on  2021/9/22.
 */
@Slf4j
@RestController
public class UserController {

    AtomicInteger idAdder = new AtomicInteger();
    Map<String, UserDto> map = new LinkedHashMap<>();
    private static final String
            ID_PREFIX = "id-";

    @PostMapping("user/create")
    public UserDto createUser(
            @RequestBody UserDto user
    ) {
        log.info("create, user: [{}]", user);

        user.setId(ID_PREFIX + idAdder.incrementAndGet())
                .setStatus("new");
        map.put(user.getId(), user);

        return user;
    }

    // http://localhost:9066/user/find-all
    @GetMapping("user/find-all")
    public List<UserDto> findAll() throws Exception {
        log.info("--------------");
        this.initData();
        Thread.sleep(1000L);
        List<UserDto> users = map.values()
                .stream()
                .collect(Collectors.toList());
        users.forEach(user -> log.info("返回的 user: [ {} ]", user));
        return users;
    }

    @GetMapping("user/find-one/{id}")
    public UserDto findOne(
            @PathVariable String id,
            @RequestHeader(required = false) String token
    ) {
        log.info("find one, id: [{}], token: [{}]", id, token);
        if (!LoginController.TOKEN.equalsIgnoreCase(token)) {
            return new UserDto().setRemark("Token 无效");
        }
        this.initData();
        return map.get(id);
    }

    @PutMapping("user/update")
    public Map<String, Object> updateUser(
            @RequestBody UserDto user
    ) {
        log.info("update, user: [{}]", user);

        if (!map.containsKey(user.getId())) {
            return MapBuilder.<String, Object>create()
                    .put("res", 0)
                    .put("msg", "用户不存在")
                    .build();
        }

        user.setStatus("update");
        map.put(user.getId(), user);

        return MapBuilder.<String, Object>create()
                .put("res", 1)
                .put("msg", "用户更改成功")
                .build();
    }

    @PostMapping("user/batch-update")
    public Map<String, Object> batchUpdateUser(
            @RequestBody List<UserDto> users
    ) {
        log.info("--------------");
        users.forEach(user -> {
            user.setStatus("update");
            log.info("要修改的 user: [ {} ]", user);

            if (!map.containsKey(user.getId())) {
                log.warn("用户不存在！user: [ {} ]", user);
                return;
            }
            user.setStatus("update");
            map.put(user.getId(), user);
        });

        return MapBuilder.<String, Object>create()
                .put("res", 1)
                .put("msg", "用户更改成功")
                .build();
    }

    @DeleteMapping("user/delete/{id}")
    public Map<String, Object> deleteUser(
            @PathVariable String id
    ) {
        log.info("delete, id: [{}]", id);

        if (!map.containsKey(id)) {
            return MapBuilder.<String, Object>create()
                    .put("res", 0)
                    .put("msg", "用户不存在")
                    .build();
        }

        map.get(id).setStatus("deleted");

        return MapBuilder.<String, Object>create()
                .put("res", 1)
                .put("msg", "用户逻辑删除成功")
                .build();
    }

    private void initData() {
        if (map.isEmpty()) {
            IntStream.rangeClosed(1, 3)
                    .mapToObj(i -> new UserDto()
                            .setId(ID_PREFIX + idAdder.incrementAndGet())
                            .setName("test-" + i)
                            .setAge(32 + i)
                            .setRemark("系统创建")
                            .setStatus("auto-new")
                    )
                    .forEach(dto -> map.put(dto.getId(), dto));
        }
    }

}
