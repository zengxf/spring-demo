package cn_zxf_test.utils;

import cn.hutool.core.util.StrUtil;
import jakarta.servlet.http.HttpServletRequest;

/**
 * <p/>
 * ZXF 创建于 2025/1/21
 */
public class IpUtils {

    private static final String[] HEADERS_TO_TRY = {
            "x-forwarded-for",
            "proxy-client-ip",
            "wl-proxy-client-ip",
            "http_x_forwarded_for",
            "http_x_forwarded",
            "http_x_cluster_client_ip",
            "http_client_ip",
            "http_forwarded_for",
            "http_forwarded",
            "http_via",
            "remote_addr",
            "x-real-ip",
            "x1-real-ip",
    };

    /*** 提取客户端 IP 地址 (可以穿透代理) */
    public static String clientIp(HttpServletRequest request) {
        for (String header : HEADERS_TO_TRY) {
            String ip = request.getHeader(header);
            if (!isEffective(ip)) {
                continue;
            }
            if (ip.contains(",")) {
                String[] array = ip.split(",");
                for (String str : array) {
                    if (isEffective(str)) {
                        ip = str;
                        break;
                    }
                }
            }
            return ip.trim();
        }
        return request.getRemoteAddr();
    }

    private static boolean isEffective(String remoteAddr) {
        if (StrUtil.isNotBlank(remoteAddr) && !"unknown".equalsIgnoreCase(remoteAddr.trim())) {
            return true;
        }
        return false;
    }

}
