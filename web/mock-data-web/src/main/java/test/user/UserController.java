package test.user;

import cn.hutool.core.map.MapBuilder;
import org.apache.catalina.User;
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
@RestController
public class UserController {

    AtomicInteger idAdder = new AtomicInteger();
    Map<String, UserDto> map = new LinkedHashMap<>();

    @PostMapping("user/create")
    public UserDto createUser(
            @RequestBody UserDto user
    ) {
        user.setId("id-" + idAdder.incrementAndGet())
                .setStatus("new");
        map.put(user.getId(), user);
        return user;
    }

    @GetMapping("user/find-all")
    public List<UserDto> findAll() {
        this.initData();
        return map.values()
                .stream()
                .collect(Collectors.toList());
    }

    @GetMapping("user/find-one/{id}")
    public UserDto findOne(
            @PathVariable String id,
            @RequestHeader(required = false) String token
    ) {
        if (!LoginController.TOKEN.equalsIgnoreCase(token)) {
            return new UserDto().setRemark("Token 无效");
        }
        this.initData();
        return map.get(id);
    }

    private void initData() {
        if (map.isEmpty()) {
            IntStream.rangeClosed(1, 3)
                    .mapToObj(i -> new UserDto()
                            .setId("id-" + idAdder.incrementAndGet())
                            .setName("test-" + i)
                            .setAge(32 + i)
                            .setRemark("系统创建")
                            .setStatus("auto-new")
                    )
                    .forEach(dto -> map.put(dto.getId(), dto));
        }
    }

    @PutMapping("user/update")
    public Map<String, Object> updateUser(
            @RequestBody UserDto user
    ) {
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

    @DeleteMapping("user/delete/{id}")
    public Map<String, Object> deleteUser(
            @PathVariable String id
    ) {
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

}
