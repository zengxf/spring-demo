package cn.zxf.sentinel.cluster.config;

import lombok.AllArgsConstructor;

import java.util.stream.Stream;

/**
 * <p/>
 * Created by ZXFeng on 2026/3/4
 */
@AllArgsConstructor
public enum RuleType implements RuleConstants {

    QPS(1, SUFFIX_QPS),
    TRAFFIC(2, SUFFIX_TRAFFIC),
    ;

    public final Integer code;
    public final String suffix;

    public static RuleType of(Integer code) {
        return Stream.of(values())
                .filter(e -> e.code.equals(code))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Not found rule type, code = " + code));
    }

}
