package test.user;

import cn.hutool.core.map.MapBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <br/>
 * Created by ZXFeng on  2021/9/22.
 */
@RestController
public class LoginController {

    static String TOKEN = "ok-123";

    @PostMapping("/admin/login")
    public Map<String, Object> login(
            @RequestBody Map<String, String> body
    ) {
        String username = body.get("username");
        String password = body.get("password");

        if ("zxf".equalsIgnoreCase(username)
                && "123".equalsIgnoreCase(password)) {

            return MapBuilder.<String, Object>create()
                    .put("res", 1)
                    .put("msg", "登录成功")
                    .put("token", TOKEN)
                    .build();
        }

        return MapBuilder.<String, Object>create()
                .put("res", 0)
                .put("msg", "账号或密码错误")
                .build();
    }

}
