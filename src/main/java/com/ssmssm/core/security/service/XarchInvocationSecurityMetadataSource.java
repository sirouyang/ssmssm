package com.ssmssm.core.security.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;

import com.ssmssm.core.utils.AntPathRequestMatcher;
import com.ssmssm.core.utils.ComConst;
import com.ssmssm.daoEx.system.UserRoleResExMapper;
import com.ssmssm.entityEx.system.RoleResConditionEntity;
import com.ssmssm.entityEx.system.RoleResResultEntity;

@Service("XarchInvocationSecurityMetadataSource")
public class XarchInvocationSecurityMetadataSource implements
		FilterInvocationSecurityMetadataSource {
	/** SAME_LEVEL_TYPE(String): 忽略参数. */
	private static final String SAME_LEVEL_TYPE = "02";

	/** ALL_TYPE(String):忽略子目录. */
	private static final String ALL_TYPE = "03";

	/** USER_TYPE(String):登录后可访问. */
	private static final String USER_TYPE = "04";

	/** resourceMap(Map<String,Collection<ConfigAttribute>>):资源权限缓存集合. */
	private volatile static Map<String, Collection<ConfigAttribute>> resourceMap = null;

	/** denyAll(boolean):默认拒绝所有访问. */
	private boolean denyAll = true;

	/** 每次都从数据库获取资源权限. */
	private boolean awaysLoadFromDB = false;

	/** 进行一次刷新. */
	private static boolean doFlush = false;

	/** denyAttr(Collection<ConfigAttribute>):拒绝访问时返回的权限. */
	private static Collection<ConfigAttribute> denyAttr = null;

	@Autowired
	private UserRoleResExMapper tAiotUserRoleResExMapper;

	/**
	 * <p>
	 * 描述：清除缓存
	 * </p>
	 * 
	 * @return
	 */
	private Map<String, Collection<ConfigAttribute>> clearCache() {
		return loadResourceDefine();
	}

	/**
	 * <p>
	 * 描述：刷新缓存
	 * </p>
	 */
	public static synchronized void reFlush() {
		doFlush = true;
	}

	/**
	 * <p>
	 * 描述：检查是否要刷新缓存
	 * </p>
	 * 
	 * @return Map<String, Collection<ConfigAttribute>>
	 */
	private synchronized Map<String, Collection<ConfigAttribute>> checkIfNeedDoFlush() {
		if (doFlush) {
			doFlush = false;
			resourceMap = clearCache();
		}

		if (resourceMap != null && resourceMap.size() > 0) {
			Map<String, Collection<ConfigAttribute>> resourceMapClone = new HashMap<String, Collection<ConfigAttribute>>(
					resourceMap.size());
			resourceMapClone.putAll(resourceMap);
			return resourceMapClone;
		}

		return null;
	}

	/**
	 * 
	 * @param denyAll
	 *            the denyAll to set
	 */
	@Autowired(required = false)
	@Qualifier("securityDenyAll")
	public void setDenyAll(Boolean denyAll) {
		this.denyAll = denyAll;
	}

	/**
	 * <p>
	 * 描述：获取属性
	 * </P>
	 * 
	 * @param object
	 * @return Collection<ConfigAttribute>
	 */
	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {
		Map<String, Collection<ConfigAttribute>> resourceMap = null;

		if (awaysLoadFromDB) {
			resourceMap = clearCache();
		} else {
			resourceMap = checkIfNeedDoFlush();
		}

		if (resourceMap != null) {
			Iterator<String> ite = resourceMap.keySet().iterator();
			Collection<ConfigAttribute> atts = null;

			while (ite.hasNext()) {
				String resURL = ite.next();
				AntPathRequestMatcher matcher = new AntPathRequestMatcher(
						resURL);

				if (matcher.matches(object)) {
					if (atts == null) {
						atts = new ArrayList<ConfigAttribute>();
					}

					atts.addAll(resourceMap.get(resURL));
				}
			}

			if (atts != null) {
				return atts;
			}
		}

		if (denyAll) {
			return denyAttr;
		}

		return null;
	}

	/**
	 * <p>
	 * 描述：获取全部配置
	 * </P>
	 * 
	 * @return Collection<ConfigAttribute>
	 */
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		if (resourceMap == null) {
			resourceMap = loadResourceDefine();
		}
		synchronized (ArrayList.class) {
			if (denyAttr == null) {
				denyAttr = new ArrayList<ConfigAttribute>(1);
				denyAttr.add(new SecurityConfig("--DENYALL--"));
			}
		}

		return null;
	}

	/**
	 * <p>
	 * 描述：supports
	 * </P>
	 */
	public boolean supports(Class<?> clazz) {
		return true;
	}

	/**
	 * <p>
	 * 描述：加载系统资源权限
	 * </P>
	 * 
	 * @return Map<String, Collection<ConfigAttribute>>
	 */
	private Map<String, Collection<ConfigAttribute>> loadResourceDefine() {

		RoleResConditionEntity condition = new RoleResConditionEntity();
		condition.setDelFlg0(ComConst.DEL_FLG_0);
		condition.setDelFlgR(ComConst.DEL_FLG_R);
		condition.setRoleEnabled(ComConst.ENABLED_1001);
		List<RoleResResultEntity> resList = tAiotUserRoleResExMapper
				.selectRoleRes(condition);
		if (resList != null && !resList.isEmpty()) {
			resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
			Collection<ConfigAttribute> atts = null;
			String role = null;
			String resUrl = null;
			String resType = null;

			for (RoleResResultEntity roleResource : resList) {
				role = roleResource.getRoleId();
				resUrl = roleResource.getResourceUrl();
				resType = roleResource.getResourceType();
				String suf = "";

				if (SAME_LEVEL_TYPE.equals(resType)) {
					suf = "*";
				}
				if (ALL_TYPE.equals(resType)) {
					suf = "**/**";
				}

				if (resourceMap.containsKey(resUrl + suf)) {
					atts = resourceMap.get(resUrl + suf);
				} else {
					atts = new ArrayList<ConfigAttribute>();
					resourceMap.put(resUrl + suf, atts);
				}

				atts.add(new SecurityConfig(role));

				if (USER_TYPE.equals(resType)
						&& !XarchUserDetailService.USER_ROLE.equals(role)) {
					atts.add(new SecurityConfig(
							XarchUserDetailService.USER_ROLE));
				}
			}

			atts = null;
		}
		return resourceMap;
	}

	/**
	 * <p>
	 * 描述：设置是否每次均从数据库动态获取权限，默认为false
	 * </P>
	 * 
	 * @param awaysLoadFromDB
	 */
	@Autowired(required = false)
	@Qualifier("securityAwaysLoadFromDB")
	public void setAwaysLoadFromDB(boolean awaysLoadFromDB) {
		this.awaysLoadFromDB = awaysLoadFromDB;
	}
}