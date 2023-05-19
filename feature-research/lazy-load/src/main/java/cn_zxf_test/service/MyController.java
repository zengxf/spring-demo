package cn_zxf_test.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <br>
 * Created by ZXFeng on 2023/5/19
 */
@RestController
@RequestMapping("/api/my")
@Slf4j
public class MyController {

    @Autowired
    private My1Service my1Service;
    @Autowired
    private My2Service my2Service;
    @Autowired
    private My3Service my3Service;
    @Autowired
    private My4Service my4Service;

    // http://localhost:9001/api/my/hello
    @GetMapping("hello")
    public String hello() {
        log.info("service: [{}]", my1Service);
        log.info("service: [{}]", my2Service);
        log.info("service: [{}]", my3Service);
        log.info("service: [{}]", my4Service);
        return "hello";
    }

}
