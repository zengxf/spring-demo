package test.user;

import cn.hutool.core.map.MapBuilder;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <br/>
 * Created by ZXFeng on  2021/9/22.
 */
@Slf4j
@RestController
public class LoginController {

    public static final String
            TOKEN = "ok-123",
            USERNAME = "zxf",
            PASSWORD = "123";

    @PostMapping("/admin/login")
    public Map<String, Object> login(
            @RequestBody Map<String, String> body,
            HttpServletResponse response
    ) {
        String username = body.get("username");
        String password = body.get("password");

        log.info("login, body: [{}], username: [{}], password: [{}]", body, username, password);

        if (USERNAME.equalsIgnoreCase(username)
                && PASSWORD.equalsIgnoreCase(password)) {
            log.info("登录成功！");
            response.addHeader("token", TOKEN);
            return MapBuilder.<String, Object>create()
                    .put("res", 1)
                    .put("msg", "登录成功")
                    .put("token", TOKEN)
                    .build();
        }

        log.info("登录失败！");
        return MapBuilder.<String, Object>create()
                .put("res", 0)
                .put("msg", "账号或密码错误")
                .build();
    }

}
