package com.zte.msm.frame.util.http;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.zte.msm.frame.util.json.JacksonUtil;

public class HttpUtil {
	
	/**
	 * 获得客户端真实IP地址的方法
	 * 在转发请求的HTTP头信息中，增加了X－FORWARDED－FOR信息。用以跟踪 原有的客户端IP地址和原来客户端请求的服务器地址。
	 * request方法客户端IP: request.getRemoteAddr() 输出：192.168.0.106
		客户端主机名：request.getRemoteHost()输出：abc
	 * @param request
	 * @return
	 */
	public static String getClientIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr()+"_"+request.getRemoteHost();
		}
		return ip;
	}
	
	/**
	 *	Web服务器名字：request.getServerName()输出：192.168.0.1
		服务器监听的端口：request.getServerPort()输出：8080
	 * @param request
	 * @return
	 */
	public static String getServerIpAddr(HttpServletRequest request){
		StringBuffer ipAddr = new StringBuffer();
		ipAddr.append(request.getServerName()).append(":").append(request.getServerPort());
		return ipAddr.toString();
	}
	
	/**
	 * 获取请求头信息
	 * @param request
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String getHeadInfo(HttpServletRequest request){
		Map<String,Object> headMap = new HashMap<String,Object>();
		Enumeration e = request.getHeaderNames();
        while (e.hasMoreElements()) {
            String name = (String) e.nextElement();
            String value = request.getHeader(name);
            headMap.put(name, value);
        }
        return JacksonUtil.toJson(headMap);
	}

}
