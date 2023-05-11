package cn.zxf.mybatis_starter.test.user;

import cn.zxf.mybatis_starter.test.mapper.UserXmlMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * <br>
 * Created by ZXFeng on 2023/4/17
 */
@RestController
@Slf4j
public class DemoController {

    @Autowired
    UserXmlMapper mapper;

    // http://localhost:8866/result/get
    @GetMapping("/result/get")
    public Map<String, Object> get() {
        Integer status = mapper.findOneStatus("zxf", 20);
        return Map.of(
                "res", "OK",
                "status", status
        );
    }

    // http://localhost:8866/demo/getUsers
    @GetMapping("/demo/getUsers")
    public List<User> getUsers() {
        List<User> users = mapper.findList();
        return users;
    }

}
