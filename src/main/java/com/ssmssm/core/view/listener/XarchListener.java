package com.ssmssm.core.view.listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.security.web.authentication.session.ConcurrentSessionControlStrategy;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ssmssm.core.plugin.XarchPluginFit;
import com.ssmssm.core.utils.FileUtilEx;



/**
 * 框架上下文监听类.
 * <p>描述:负责初始化上下文环境</p>
 */
@SuppressWarnings("deprecation")
public class XarchListener implements ServletContextListener {
	/** 插件整合配置参数名称. */
	private final String PLUGINFIT_PARAM_KEY = "xarchPluginFitConfigLocation";
	
	/** 默认要初始化的插件. */
	private final String DEFAULT_PLUGINFIT = "authsystemPluginFit,workflowPluginFit";
	
	/** Spring框架上下文. */
	private static ApplicationContext ctx = null;
	/** 应用绝对路径. */
	public static String appAbsolutepath = "";
	
	/** 整合系统初始化状态. */
	private static final Map<String, Boolean> initState = new HashMap<String, Boolean>(3);
	
	private static final List<ServletContextListener> listeners = new ArrayList<ServletContextListener>();
	
	/**
	 * 从Spring中获取Bean.
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(Class<T> beanClass) {
		String[] beanNames = ctx.getBeanNamesForType(beanClass);
		
		if (beanNames != null && beanNames.length > 0) {
			String className = beanClass.getSimpleName();
			
			for (String beanName : beanNames) {
				if (className.equalsIgnoreCase(beanName)) {
					return (T)getBean(beanName);
				}
			}
			
			return (T)getBean(beanNames[beanNames.length-1]);
		}
		
		return null;
	}
	
	/**
	 * 从Spring中获取Bean.
	 */
	public static Object getBean(String beanName) {
		return ctx.getBean(beanName);
	}
	
	/**
	 * 从Spring中获取Bean.
	 * @return 如果存在则返回对应的Bean；如果不存在则返回null
	 */
	public static Object getBeanNotRequired(String beanName) {
		if (ctx != null && ctx.containsBean(beanName)) {
			return getBean(beanName);
		}
		
		return null;
	}
	
	/**
	 * 获取Spring Bean注册器.
	 */
	public static BeanDefinitionRegistry getBeanDefinitionRegistry() {
		if (ctx != null) {
			ConfigurableApplicationContext configurableApplicationContext = (ConfigurableApplicationContext) ctx;
			return (BeanDefinitionRegistry) configurableApplicationContext.getBeanFactory();
		}
		
		return null;
	}
	
	/**
	 * <p>初始化Spring配置文件.</p>
	 * @param configLocations Spring配置文件
	 */
	public static void initContext(String... configLocations) {
		ctx = new FileSystemXmlApplicationContext(configLocations);
	}
	
	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent event) {
		ctx = null;
		unbindResource();
	}
	
	/**
	 * 释放Spring Data事务资源.
	 */
	private void unbindResource() {
		Map<Object, Object> resource = TransactionSynchronizationManager.getResourceMap();
		
		if (resource != null) {
			for (Object key : resource.keySet()) {
				TransactionSynchronizationManager.unbindResourceIfPossible(key);
			}
		}
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent event) {
		System.setProperty("file.encoding", "UTF-8");
		ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(event.getServletContext());
		XarchListener.appAbsolutepath = FileUtilEx.ConvertPath(event.getServletContext().getRealPath("/"));
		
		initPlugin(event.getServletContext().getInitParameter(PLUGINFIT_PARAM_KEY));
		
		initSecurity();
		fireEvent(event);
	}
	
	/**
	 * 添加监听器.
	 * @param listener 要添加的监听器
	 */
	public static void addListener(ServletContextListener listener) {
		if (!listeners.contains(listener)) {
			listeners.add(listener);
		}
	}
	
	/**
	 * 触发监听器.
	 */
	public void fireEvent(ServletContextEvent event) {
		for (ServletContextListener listener : listeners) {
			listener.contextInitialized(event);
		}
	}
	
	/**
	 * 初始化Spring Security.
	 */
    private void initSecurity() {
		try {
			Integer maxSessions = (Integer)getBeanNotRequired("com.ces.xarch.security.maxSessions");
			
			if (maxSessions != null) {
				ConcurrentSessionControlStrategy bean = getBean(ConcurrentSessionControlStrategy.class);
				bean.setMaximumSessions(maxSessions);
			}
		} catch (Exception ex) {
			// 没有找到Spring Security Session 管理类
		}
	}
	
	/**
	 * 初始化插件.
	 * @param fitKey 要初始化的插件BeanID，多个用“,”进行分隔
	 */
	private void initPlugin(String fitKey) {
		if (fitKey == null) {
			fitKey = "";
		}
		
		fitKey = DEFAULT_PLUGINFIT + "," + fitKey;
		String[] plugins = fitKey.split(",");
		
		for (String key : plugins) {
			if (key != null && !"".equals(key.trim())) {
				if (initState.get("__xarch_plugin_"+key) == null) {
					initState.put("__xarch_plugin_"+key, false);
				}
				
				if (!initState.get("__xarch_plugin_"+key)) {
					try {
						XarchPluginFit pluginFit = (XarchPluginFit)getBean(key);
						pluginFit.fit();
					} catch (Exception ex) {
						// 无需抛出异常，出现该异常说明没有对应插件
					} finally {
						initState.put("__xarch_plugin_"+key, true);
					}
				}
			}
		}
	}
	
	/**
	 * 验证给定插件是否已经初始化.
	 * @param key 插件代码
	 * @return true:已经初始化；false：未初始化
	 */
	public static boolean isPluginInit(String key) {
		if (initState.get("__xarch_plugin_"+key) == null) {
			return false;
		}
		
		return initState.get("__xarch_plugin_"+key);
	}
	
	/**
	 * 设置插件初始化状态为已初始化.
	 * @param key 插件代码
	 */
	public static void pluginInit(String key) {
		initState.put("__xarch_plugin_"+key, true);
	}
}

