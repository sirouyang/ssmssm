package com.ssmssm.core.security.handler;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import com.ssmssm.core.logger.LoggerService;
import com.ssmssm.core.utils.WebUtilsEx;
import com.ssmssm.entity.system.BusinessLogs;
import com.ssmssm.entity.system.User;

public class LogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

	@Autowired
	private LoggerService loggerService;
	
	@Override
	public void onLogoutSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		
		if (authentication != null) {
			BusinessLogs businessLogs = new BusinessLogs();
			User user = (User) authentication.getPrincipal();
			businessLogs.setModelName("登录系统");
			businessLogs.setActionType("退出");
			businessLogs.setUserId(user.getId());
			businessLogs.setIpAddress(WebUtilsEx.getIpAddr(request).concat("||")
					.concat(request.getRequestURI()));
			businessLogs.setOpTarget("退出系统：".concat(user.getUserName()));
			businessLogs.setOpResult("成功");
			businessLogs.setOpTime(new Date());

			loggerService.save(businessLogs);
		}
		
		// 将登录名从Cookie中清除
		Cookie cookie = new Cookie(AuthenticationSuccessHandler.LOGINUSERNAME_COOKIEKEY, null);
		cookie.setPath(request.getContextPath());
		response.addCookie(cookie);
		
		super.onLogoutSuccess(request, response, authentication);
	}
}
