package cn.zxf.sentinel.cluster.gateway.controller;

import cn.zxf.sentinel.cluster.gateway.config.RuleLoadHelper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Set;

/**
 * <p/>
 * Created by ZXFeng on 2026/3/2
 */
@RestController
@Slf4j
public class RuleController {

    @Resource
    private RuleLoadHelper ruleLoadHelper;

    // http://localhost:9641/rule/reload
    @GetMapping("/rule/reload")
    public Map<String, Object> reload() {
        Set<String> ruleKeys = ruleLoadHelper.reloadRules();
        return Map.of(
                "res", "OK",
                "ruleKeys", ruleKeys,
                "status", 200
        );
    }

}
