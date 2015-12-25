package com.ssmssm.core.security.handler;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import com.ssmssm.core.logger.LoggerService;
import com.ssmssm.core.utils.WebUtilsEx;
import com.ssmssm.entity.system.BusinessLogs;

public class AuthenticationFailureHandler extends
		SimpleUrlAuthenticationFailureHandler {

	@Autowired
	private LoggerService loggerService;

	@SuppressWarnings("deprecation")
	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {

		BusinessLogs businessLogs = new BusinessLogs();
		businessLogs.setModelName("登录系统");
		businessLogs.setActionType("登录");
		businessLogs.setUserId(exception.getAuthentication().getName());
		businessLogs.setIpAddress(WebUtilsEx.getIpAddr(request).concat("||")
				.concat(request.getRequestURI()));
		businessLogs.setOpTarget("用户名："
				.concat(exception.getAuthentication().getName()).concat(" 密码：")
				.concat(request.getParameter("password")));
		businessLogs.setOpResult("失败");
		businessLogs.setOpTime(new Date());

		loggerService.save(businessLogs);

		super.onAuthenticationFailure(request, response, exception);
	}
}
