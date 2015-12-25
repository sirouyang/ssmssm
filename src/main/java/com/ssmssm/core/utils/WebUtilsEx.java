package com.ssmssm.core.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * 页面处理工具类.
 */
public class WebUtilsEx {
	private WebUtilsEx() {
	}

	/**
	 * 获取IP地址.
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = (String) request.getSession().getAttribute(
				ServletUtilsEx.IP_KEY);

		if (ip == null) {
			ip = request.getHeader("x-forwarded-for");

			if (ip != null && !"unknown".equalsIgnoreCase(ip)) {
				ip = ip.split(",")[0];
			}

			if (ip == null || ip.length() == 0
					|| "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("Proxy-Client-IP");
			}
			if (ip == null || ip.length() == 0
					|| "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("WL-Proxy-Client-IP");
			}
			if (ip == null || ip.length() == 0
					|| "unknown".equalsIgnoreCase(ip)) {
				ip = request.getRemoteAddr();
			}

			request.getSession().setAttribute(ServletUtilsEx.IP_KEY, ip);
		}

		return ip;
	}
}
