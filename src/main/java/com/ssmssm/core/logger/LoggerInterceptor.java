package com.ssmssm.core.logger;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.opensymphony.xwork2.ActionSupport;
import com.ssmssm.core.utils.ComConst;
import com.ssmssm.core.utils.WebUtilsEx;
import com.ssmssm.core.view.BaseController;
import com.ssmssm.entity.system.BusinessLogs;
import com.ssmssm.entity.system.User;

/**
 * 操作日志记录拦截器
 * <p>
 * 描述：负责记录用户操作日志
 * </p>
 */
public class LoggerInterceptor extends HandlerInterceptorAdapter {
    private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(this.getClass().getName());
    
    private Boolean isLogger; // 是否写Logger
    
    private Boolean isShowErrors; // 是否显示错误
    
    @Autowired
    private LoggerService loggerService;
    
    // 重写 preHandle()方法，在业务处理器处理请求之前对该请求进行拦截处理
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception {
        if (isLogger) {
            BusinessLogs log = null;
            String opError = "失败";
            Logger businessLogger = null;
            Logger classLogger = null;
            Object action = null;
            if (handler instanceof HandlerMethod) {
                HandlerMethod handlerMethod = (HandlerMethod)handler;
                Method method = handlerMethod.getMethod();
                try {
                    
                    // 获取注解信息
                    businessLogger = method.getAnnotation(Logger.class);
                    classLogger = handlerMethod.getBean().getClass().getAnnotation(Logger.class);
                    // 从方法中获取日志信息
                    if (businessLogger != null && businessLogger.log() && !businessLogger.globaldisable()) {
                        if (classLogger == null || (classLogger != null && !classLogger.globaldisable())) {
                            opError = businessLogger.opError();
                            
                            log = createLogEntity();
                            log.setModelName(businessLogger.modelName());
                            log.setActionType(businessLogger.actionType());
                            log.setOpResult(businessLogger.opSucess());
                        }
                    }
                    
                    // 从类中获取日志信息
                    if (log == null) {
                        if (classLogger != null && classLogger.log() && !classLogger.globaldisable()
                            && businessLogger != null) {
                            opError = businessLogger.opError();
                            
                            log = createLogEntity();
                            log.setModelName(classLogger.modelName());
                            log.setActionType(classLogger.actionType());
                            log.setOpResult(classLogger.opSucess());
                        }
                    }
                    
                    if (log != null) {
                        if (StringUtils.isEmpty(log.getModelName())) {
                            if (classLogger != null) {
                                log.setModelName(classLogger.modelName());
                            }
                            else {
                                log.setModelName(request.getRequestURI());
                            }
                        }
                        
                        if (StringUtils.isEmpty(log.getActionType())) {
                            if (classLogger != null) {
                                log.setActionType(classLogger.actionType());
                            }
                            else {
                                log.setActionType(method.getName());
                            }
                        }
                    }
                    
                    if (action instanceof ActionSupport) {
                        ActionSupport act = (ActionSupport)action;
                        
                        if (act.hasErrors()) {
                            log.setOpResult(opError);
                            
                            if (isShowErrors) {
                                String errors = StringUtils.EMPTY;
                                
                                if (!act.getActionErrors().isEmpty()) {
                                    for (String error : act.getActionErrors()) {
                                        if (!StringUtils.isEmpty(errors)) {
                                            errors += "\r\n";
                                        }
                                        errors += error;
                                    }
                                }
                                if (!act.getFieldErrors().isEmpty()) {
                                    Map<String, List<String>> errorMap = act.getFieldErrors();
                                    
                                    for (String fieldName : errorMap.keySet()) {
                                        if (!StringUtils.isEmpty(errors)) {
                                            errors += "\r\n";
                                        }
                                        
                                        errors += fieldName + "：";
                                        
                                        for (String error : errorMap.get(fieldName)) {
                                            errors += error;
                                        }
                                    }
                                }
                                
                                throw new Exception(errors);
                            }
                        }
                    }
                }
                catch (Exception ex) {
                    // 获取业务日志出现异常时不应该影响业务运行，因此将异常信息写入Log4j，而不抛出异常
                    logger.warn("分析业务操作日志出现异常", ex);
                }
                finally {
                    try {
                        if (log != null) {
                            if (action instanceof BaseController) {
                                log.setOpTarget(((BaseController)action).getLogger());
                            }
                            
                            if (StringUtils.isEmpty(log.getOpTarget())) {
                                if (businessLogger != null) {
                                    log.setOpTarget(businessLogger.logger());
                                }
                                else if (classLogger != null) {
                                    log.setOpTarget(classLogger.logger());
                                }
                            }
                            log.setDelFlg(ComConst.DEL_FLG_0);
                            log.setSysRegUsrCd(ComConst.SYSTEM_USER);
                            log.setSysRegTmsp(log.getOpTime());
                            log.setSysUpdUsrCd(ComConst.SYSTEM_USER);
                            log.setSysUpdTmsp(log.getOpTime());
                            loggerService.save(log);
                            
                        }
                    }
                    catch (Exception e) {
                        // 记录业务日志出现异常时不应该影响业务运行，因此将异常信息写入Log4j，而不抛出异常
                        logger.warn("记录业务操作日志出现异常", e);
                    }
                }
            }
        }
        return true;
    }
    
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mav)
        throws Exception {
        if (isLogger) {
            // System.out.println("This is postHandle!");
        }
    }
    
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
        Exception excptn)
        throws Exception {
        if (isLogger) {
            // System.out.println("This is afterCompletion!");
        }
    }
    
    /**
     * 创建操作日志实体.
     * 
     */
    private BusinessLogs createLogEntity() {
        BusinessLogs log = new BusinessLogs();
        ServletRequestAttributes attr = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
        log.setIpAddress(WebUtilsEx.getIpAddr(attr.getRequest()).concat("||").concat(attr.getRequest().getRequestURI()));
        log.setOpTime(new Date());
        
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            log.setUserId(((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());
        }
        
        return log;
    }
    
    /**
     * @return the isLogger
     */
    public Boolean getIsLogger() {
        return isLogger;
    }
    
    /**
     * @return the isShowErrors
     */
    public Boolean getIsShowErrors() {
        return isShowErrors;
    }
    
    /**
     * @param isShowErrors the isShowErrors to set
     */
    public void setIsShowErrors(Boolean isShowErrors) {
        this.isShowErrors = isShowErrors;
    }
    
    /**
     * @param isLogger the isLogger to set
     */
    public void setIsLogger(Boolean isLogger) {
        this.isLogger = isLogger;
    }
    
}