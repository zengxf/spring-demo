package com.example.mcp.controller;

import com.example.mcp.tools.CalculatorTool;
import com.example.mcp.tools.WeatherTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试控制器 - 用于测试 MCP 工具
 */
@RestController
@RequestMapping("/api/test")
public class TestController {

    @Autowired
    private CalculatorTool calculatorTool;
    @Autowired
    private WeatherTool weatherTool;

    /**
     * 测试加法
     */
    @GetMapping("/add")
    public String testAdd(@RequestParam double a, @RequestParam double b) {
        double result = calculatorTool.add(a, b);
        return String.format("%.2f + %.2f = %.2f", a, b, result);
    }

    /**
     * 测试减法
     */
    @GetMapping("/subtract")
    public String testSubtract(@RequestParam double a, @RequestParam double b) {
        double result = calculatorTool.subtract(a, b);
        return String.format("%.2f - %.2f = %.2f", a, b, result);
    }

    /**
     * 测试乘法
     */
    @GetMapping("/multiply")
    public String testMultiply(@RequestParam double a, @RequestParam double b) {
        double result = calculatorTool.multiply(a, b);
        return String.format("%.2f × %.2f = %.2f", a, b, result);
    }

    /**
     * 测试除法
     */
    @GetMapping("/divide")
    public String testDivide(@RequestParam double a, @RequestParam double b) {
        try {
            double result = calculatorTool.divide(a, b);
            return String.format("%.2f ÷ %.2f = %.2f", a, b, result);
        } catch (IllegalArgumentException e) {
            return "错误: " + e.getMessage();
        }
    }

    /**
     * 测试天气查询
     */
    @GetMapping("/weather")
    public String testWeather(@RequestParam String city) {
        return weatherTool.getWeather(city);
    }

    /**
     * 获取支持的城市列表
     */
    @GetMapping("/cities")
    public String testCities() {
        return weatherTool.getSupportedCities();
    }
}
