package cn.tofucat.toolkit.util;

import javax.servlet.http.HttpServletRequest;

/**
 * zzzxb
 * 2023/5/5
 */
public class RemoteAddr {

    /**
     * 获取客户端IP地址
     * @param forwardedFor x-forwarded-for
     * @param proxyClientIp Proxy-Client-IP
     * @param wlProxyClientIp WL-Proxy-Client-IP
     * @param remoteAddr remoteAddr
     * @return ip
     */
    public static String getIpAddr(String forwardedFor, String proxyClientIp, String wlProxyClientIp, String remoteAddr) {
        String ip = checkIp(null, forwardedFor);
        ip = checkIp(ip, proxyClientIp);
        ip = checkIp(ip, wlProxyClientIp);
        ip = checkIp(ip, remoteAddr);

        if ((ip.contains(","))){
            ip = ip.substring(0 , ip.indexOf(","));
        }
        return ip;
    }

    public static String checkIp(String oldIp, String ip) {
        if (oldIp != null) {
            return oldIp;
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            return null;
        }
        return ip;
    }

    /**
     * 获取客户端IP地址
     * @param request request
     * @return ip
     */
    public static String getIpAddr(HttpServletRequest request) {
        return getIpAddr(request.getHeader(HeaderName.X_FORWARDED_FOR.getName()),
                request.getHeader(HeaderName.PROXY_CLIENT_IP.getName()),
                request.getHeader(HeaderName.WL_PROXY_CLIENT_IP.getName()),
                request.getRemoteAddr());
    }

    public enum HeaderName {
        X_FORWARDED_FOR("x-forwarded-for"),
        PROXY_CLIENT_IP("Proxy-Client-IP"),
        WL_PROXY_CLIENT_IP("WL-Proxy-Client-IP");

        private final String name;

        HeaderName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
