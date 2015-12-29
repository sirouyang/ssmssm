package com.ssmssm.core.utils;

import java.util.Enumeration;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletRequest;

import org.apache.commons.lang3.Validate;

/**
 * 请求工具类.
 * <p>描述:处理请求参数</p>
 */
public class ServletUtilsEx {
	/** Session中IP地址对应的键值. */
	public static final String IP_KEY = "_SSM_CACHE_IP_KEY_";
	
	/**
	 * 构造函数.
	 */
	private ServletUtilsEx() {}
	
	/**
	 * 取得带有相同前缀的Request 参数.
	 * @param request Request对象
	 * @param prefix 参数前缀名称
	 * @return 去除前缀的参数集合
	 */
	public static Map<String, Object> getParametersStartingWith(ServletRequest request, String prefix) {
		Validate.notNull(request, "Request must not be null");
		Enumeration<String> paramNames = request.getParameterNames();
		Map<String, Object> params = new TreeMap<String, Object>();
		
		if (prefix == null) {
			prefix = "";
		}
		
		while (paramNames != null && paramNames.hasMoreElements()) {
			String paramName = paramNames.nextElement();
			
			if ("".equals(prefix) || paramName.startsWith(prefix)) {
				String unprefixed = paramName.substring(prefix.length());
				String[] values = request.getParameterValues(paramName);
				if (values == null || values.length == 0) {
					// Do nothing, no values found at all.
				} else if (values.length > 1) {
					params.put(unprefixed, values);
				} else {
					params.put(unprefixed, values[0]);
				}
			}
		}
		
		return params;
	}
}
