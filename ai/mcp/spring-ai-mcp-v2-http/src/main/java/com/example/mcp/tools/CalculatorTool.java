package com.example.mcp.tools;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

/**
 * 计算器工具 - 提供基本的数学运算功能
 */
@Component
public class CalculatorTool {

    @Tool(description = "执行加法运算")
    public double add(
            @ToolParam(description = "第一个数字") double a,
            @ToolParam(description = "第二个数字") double b
    ) {
        return a + b;
    }

    @Tool(description = "执行减法运算")
    public double subtract(
            @ToolParam(description = "被减数") double a,
            @ToolParam(description = "减数") double b
    ) {
        return a - b;
    }

    @Tool(description = "执行乘法运算")
    public double multiply(
            @ToolParam(description = "第一个数字") double a,
            @ToolParam(description = "第二个数字") double b
    ) {
        return a * b;
    }

    @Tool(description = "执行除法运算")
    public double divide(
            @ToolParam(description = "被除数") double a,
            @ToolParam(description = "除数") double b
    ) {
        if (b == 0) {
            throw new IllegalArgumentException("除数不能为零");
        }
        return a / b;
    }
}
