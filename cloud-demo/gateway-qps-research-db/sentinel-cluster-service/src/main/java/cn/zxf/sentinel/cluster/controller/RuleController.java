package cn.zxf.sentinel.cluster.controller;

import cn.zxf.sentinel.cluster.config.RuleLoadHelper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p/>
 * Created by ZXFeng on 2026/3/2
 */
@RestController
@Slf4j
public class RuleController {

    @Resource
    private RuleLoadHelper ruleLoadHelper;

    // http://localhost:9651/rule/reload
    @GetMapping("/rule/reload")
    public Map<String, Object> reload() {
        ruleLoadHelper.reloadRules();
        return Map.of(
                "res", "OK",
                "status", 200
        );
    }

}
