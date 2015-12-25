package com.ssmssm.core.view;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;

public abstract class BaseController {
	
	@Autowired
	protected  HttpServletRequest request;

	/** 存储操作日志内容. */
	private StringBuilder logger;
	
	
	@ExceptionHandler
    public void exception(HttpServletRequest request, Exception e) {  
		//添加自己的异常处理逻辑，如日志记录
		request.setAttribute("exceptionMessage", e.getMessage()); 
        appendLogger(e.getMessage());
    }  

	/**
	 * 将日志重置为指定的文本.
	 * 
	 */
	protected void setLogger(String text) {
		logger = new StringBuilder(text);

	}

	/**
	 * 追加日志内容.
	 * 
	 */
	protected void appendLogger(String text) {
		if (logger == null) {
			logger = new StringBuilder();
		}

		logger.append(text);
	}

	/**
	 * 获取操作日志内容.
	 * 
	 */
	public String getLogger() {
		if (logger != null) {
			return logger.toString();
		}

		return null;
	}
}
