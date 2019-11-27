package com.server.venus.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 项目名称：venus-admin-server
 * 类名称：IpUtils
 * 类描述：获取ip地址工具类
 * 创建人：yingx
 * 创建时间： 2019/11/27
 * 修改人：yingx
 * 修改时间： 2019/11/27
 * 修改备注：
 */
public class IpUtils {

    private static final Logger logger = LoggerFactory.getLogger(IpUtils.class);

    public static String getIpAddr(HttpServletRequest request) {
        // X-Forwarded-For：Squid 服务代理
        String ip = request.getHeader("x-forwarded-for");
        if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个ip值，第一个ip才是真实ip
            if (ip.indexOf(",") != -1) {
                ip = ip.split(",")[0];
            }
        }
        // Proxy-Client-IP：apache 服务代理
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        // WL-Proxy-Client-IP：weblogic 服务代理
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        // HTTP_CLIENT_IP：有些代理服务器
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        // X-Real-IP：nginx服务代理
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        // 还是不能获取到，最后再通过request.getRemoteAddr()获取
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip.equals("127.0.0.1") || ip.equals("0:0:0:0:0:0:0:1")) {
            //根据网卡取本机配置的IP
            InetAddress inet = null;
            try {
                inet = InetAddress.getLocalHost();
            } catch (UnknownHostException e) {
                logger.error("e");
            }
            ip = inet.getHostAddress();
        }
        return ip;
    }
}
