package com.example.mcp.tools;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 天气查询工具 - 提供城市天气查询功能（模拟数据）
 */
@Component
public class WeatherTool {

    // 模拟天气数据
    private final Map<String, WeatherInfo> weatherData = new HashMap<>();

    public WeatherTool() {
        // 初始化一些模拟数据
        weatherData.put("北京", new WeatherInfo("北京", "晴", 15, 5));
        weatherData.put("上海", new WeatherInfo("上海", "多云", 18, 12));
        weatherData.put("广州", new WeatherInfo("广州", "小雨", 22, 18));
        weatherData.put("深圳", new WeatherInfo("深圳", "阴", 20, 16));
        weatherData.put("成都", new WeatherInfo("成都", "雾", 12, 8));
    }

    @Tool(description = "查询指定城市的天气信息")
    public String getWeather(
            @ToolParam(description = "城市名称，例如：北京、上海、广州") String city
    ) {

        WeatherInfo info = weatherData.get(city);

        if (info == null) {
            return String.format("抱歉，暂时没有 %s 的天气数据。支持的城市：北京、上海、广州、深圳、成都", city);
        }

        return info.toString();
    }

    @Tool(description = "获取所有支持查询的城市列表")
    public String getSupportedCities() {
        return "支持查询的城市：" + String.join("、", weatherData.keySet());
    }

    /**
     * 天气信息数据类
     */
    public record WeatherInfo(String city, String condition, int highTemp, int lowTemp) {
        @Override
        public String toString() {
            return String.format("%s 天气：%s，最高温度 %d℃，最低温度 %d℃",
                    city, condition, highTemp, lowTemp
            );
        }
    }

}
