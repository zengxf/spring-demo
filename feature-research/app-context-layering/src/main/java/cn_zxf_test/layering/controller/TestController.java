package cn_zxf_test.layering.controller;

import cn_zxf_test.layering.bean.ChildBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p/>
 * Created by ZXFeng on 2024/1/23
 */
@RestController
public class TestController {

    @Autowired
    private ChildBean childBean;

    // child1 => http://localhost:8080/test
    // child2 => http://localhost:8081/test
    // child3 => http://localhost:8082/test
    @RequestMapping("/test")
    public ChildBean getChildBean() {
        return childBean;
    }

}