package cn.zxf.sentinel.cluster.gateway.filter;

import cn.zxf.sentinel.cluster.config.RuleConstants;
import cn.zxf.sentinel.cluster.config.RuleType;
import cn.zxf.sentinel.cluster.gateway.config.RuleLoadHelper;
import com.alibaba.csp.sentinel.util.StringUtil;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * <p/>
 * Created by ZXFeng on 2026/3/4
 */
public class ResourceNameUtils {

    public static final String KEY_TENANT = "X-tenant"; // 机构 ID 头标识


    /**
     * 获取资源名称
     */
    public static String getResourceName(
            RuleLoadHelper ruleLoadHelper,
            ServerWebExchange exchange,
            RuleType type
    ) {
        ServerHttpRequest request = exchange.getRequest();
        String tenant = request.getHeaders().getFirst(KEY_TENANT);
        if (StringUtil.isBlank(tenant)) {
            return RuleConstants.NONE_RULE_SIGN;
        }

        String path = request.getPath().value();
        String urlDelimiter = "/";

        // 从路径中提取服务名和 api (格式: /service-name/...)
        if (path.startsWith(urlDelimiter)) {
            String[] parts = StringUtils.tokenizeToStringArray(path, urlDelimiter);
            if (parts.length > 1) {
                String app = parts[0]; // 服务名
                String api = urlDelimiter + Arrays.stream(parts).skip(1).collect(Collectors.joining(urlDelimiter));
                return ruleLoadHelper.findRuleKey(type, tenant, app, api);
            }
        }

        return RuleConstants.NONE_RULE_SIGN;
    }

}
