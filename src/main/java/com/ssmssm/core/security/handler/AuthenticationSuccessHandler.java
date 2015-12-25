package com.ssmssm.core.security.handler;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.ssmssm.core.logger.LoggerService;
import com.ssmssm.core.utils.WebUtilsEx;
import com.ssmssm.entity.system.BusinessLogs;
import com.ssmssm.entity.system.User;

public class AuthenticationSuccessHandler extends
		SavedRequestAwareAuthenticationSuccessHandler {
	public static final String LOGINUSERNAME_COOKIEKEY = "_basearch_loginuser_name_";

	@Autowired
	private LoggerService loggerService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {

		BusinessLogs businessLogs = new BusinessLogs();
		User user = (User) authentication.getPrincipal();
		businessLogs.setModelName("登录系统");
		businessLogs.setActionType("登录");
		businessLogs.setUserId(user.getId());
		businessLogs.setIpAddress(WebUtilsEx.getIpAddr(request).concat("||")
				.concat(request.getRequestURI()));
		businessLogs.setOpTarget("用户名：".concat(user.getUserName()));
		businessLogs.setOpResult("成功");
		businessLogs.setOpTime(new Date());

		loggerService.save(businessLogs);

		// 将登录名加入Cookie
		Cookie cookie = new Cookie(LOGINUSERNAME_COOKIEKEY, URLEncoder.encode(
				((User) authentication.getPrincipal()).getUserName(),
				"UTF-8"));
		cookie.setPath(request.getContextPath());
		response.addCookie(cookie);

		super.onAuthenticationSuccess(request, response, authentication);
	}
}
