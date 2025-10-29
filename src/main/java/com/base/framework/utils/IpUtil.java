package com.base.framework.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: 郭郭
 * @Create: 2025/10/29
 * @Description:
 **/
public class IpUtil {

    /**
     * 获取客户端真实IP地址
     * @param request HttpServletRequest
     * @return getRealIp
     */
    public static String getRealIp(HttpServletRequest request) {
        String ip = null;

        // 优先顺序非常重要
        // 1. Cloudflare CDN
        ip = request.getHeader("CF-Connecting-IP");
        if (isValidIp(ip)) {
            return ip;
        }

        // 2. 国内外主流云服务商、反向代理常用的头
        String[] headers = {
                // 标准代理头，可能包含多个IP，用逗号分隔
                "X-Forwarded-For",
                // Nginx 常用
                "X-Real-IP",
                // Apache, WebLogic 使用
                "Proxy-Client-IP",
                // WebLogic 使用
                "WL-Proxy-Client-IP",
                // 有些代理使用
                "HTTP_CLIENT_IP",
                // 注意下划线格式
                "HTTP_X_FORWARDED_FOR"
        };

        for (String header : headers) {
            ip = request.getHeader(header);
            if (isValidIp(ip)) {
                // 如果是 X-Forwarded-For，需要取第一个非 unknown 的 IP
                if (header.equals("X-Forwarded-For")) {
                    String[] ips = ip.split(",");
                    for (String strIp : ips) {
                        if (!"unknown".equalsIgnoreCase(strIp.trim())) {
                            ip = strIp.trim();
                            break;
                        }
                    }
                }
                return ip;
            }
        }

        // 3. 最后才使用 getRemoteAddr()
        ip = request.getRemoteAddr();

        // 处理本地回环地址
        if ("127.0.0.1".equals(ip) || "0:0:0:0:0:0:0:1".equals(ip)) {
            // 在开发环境下，可以接受，生产环境可能需要特殊处理
            return "127.0.0.1";
        }

        return ip;
    }

    /**
     * 验证IP地址是否有效（非null、非空、非unknown）
     * @param ip String
     * @return boolean
     */
    private static boolean isValidIp(String ip) {
        // 可以增加更严格的IP格式校验，但通常不需要，因为代理服务器会保证格式
        return ip != null && !ip.isEmpty() && !"unknown".equalsIgnoreCase(ip);
    }

}
