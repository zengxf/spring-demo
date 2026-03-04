package cn.zxf.sentinel.cluster.config;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p/>
 * Created by ZXFeng on 2026/2/12
 */
@Data
@Accessors(chain = true)
public class RulePO {

    private Integer id;         // 规则 ID

    private String tenant;      // 租户 (机构)
    private String app;         // 应用名 (可对指定应用限流)
    private String api;         // 接口 path (可对指定接口限流)

    private Integer type;       // 规则类型。1: QPS; 2: Traffic

    private Long limit;         // 限制数
    private Integer window;     // 时间窗口 (ms)

    private Boolean enable;     // 是否启用

}
