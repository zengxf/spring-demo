package com.alibaba.cloud.ai.mcp.server.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class TimeService {

    private static final Logger logger = LoggerFactory.getLogger(TimeService.class);

    @Tool(description = "获取指定城市的时间。")
    public String getCityTimeMethod(@ToolParam(description = "时区 ID，如 Asia/Shanghai") String timeZoneId) {
        logger.info("当前时区为 {}", timeZoneId);

        String timeStr = getTimeByZoneId(timeZoneId);

        return String.format("当前时区为 %s 现在的时间是 " + "%s", timeZoneId, timeStr);
    }

    private String getTimeByZoneId(String zoneId) {
        ZoneId zid = ZoneId.of(zoneId);

        ZonedDateTime zonedDateTime = ZonedDateTime.now(zid);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z");

        String formattedDateTime = zonedDateTime.format(formatter);
        logger.info("当前时间(时区)：{}", formattedDateTime);

        return formattedDateTime;
    }

}
