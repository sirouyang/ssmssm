package com.ssmssm.core.utils;

public class StringUtilsEx {
	
	/**
	 * 构造函数.
	 */
	private StringUtilsEx() {}

	/**
	 * 根据分割符将字符串进行分割. 例：
	 * 
	 * <pre>
	 * StringUtils.split(null,"${","}")  = null
	 * StringUtils.split("","${","}")  = null
	 * StringUtils.split("你好","${","}")  = String[]{"你好","",""}
	 * StringUtils.split("你好${吗？","${","}")  = String[]{"你好${吗？","",""}
	 * StringUtils.split("你好}吗？","${","}")  = String[]{"你好}吗？","",""}
	 * StringUtils.split("你好${userName}:你所在组织为${orgName}.","${","}")  = String[]{"你好","userName",":你所在组织为${orgName}."}
	 * </pre>
	 * 
	 */
	public static String[] split(String str, String separatorChars,
			String separatorEndChars) {
		if (str == null)
			return null;

		int pos = str.indexOf(separatorChars);
		int ePos = str.indexOf(separatorEndChars);

		if (pos == -1 || ePos == -1)
			return new String[] { str, "", "" };
		return new String[] { str.substring(0, pos),
				str.substring(pos + separatorChars.length(), ePos),
				str.substring(ePos + separatorEndChars.length()) };
	}
}
