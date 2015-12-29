package com.ssmssm.core.security.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ssmssm.core.utils.ComConst;
import com.ssmssm.dao.system.UserMapper;
import com.ssmssm.daoEx.system.UserRoleResExMapper;
import com.ssmssm.entity.system.User;
import com.ssmssm.entity.system.UserCriteria;
import com.ssmssm.entityEx.system.RoleResConditionEntity;

@Service("UserDetailServiceEx")
public class UserDetailServiceEx implements UserDetailsService {
	/** USER_ROLE(String):登录后用户具有的默认权限. */
	public static final String USER_ROLE = "USER_ROLE";

	/** 是否使用验证码（默认不使用）. */
	private boolean useValidateCode = false;

	/** Session中保存验证码的主键名称. */
	public static String sessionvalidateCodeKey = "_ssm_validate_code_";

	/** 表单中存放用户输入验证码的参数名称. */
	private String validateCodeParameter = "validateCode";

	/** 登录名参数名称. */
	private String usernameParameter = "username";

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private UserRoleResExMapper systemUserRoleResExMapper;

	/**
	 * <p>
	 * 描述：根据用户名获取用户详细
	 * </P>
	 * 
	 * @param username
	 * @return UserDetails
	 */
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		if (useValidateCode) {
			checkValidateCode();
		}

		// 获取用户

		UserCriteria UserCriteria = new UserCriteria();
		UserCriteria.createCriteria()
				.andDelFlgEqualTo(ComConst.DEL_FLG_0)
				.andUserEnabledEqualTo(ComConst.ENABLED_1001)
				.andUserNameEqualTo(username);
		List<User> userList = userMapper
				.selectByExample(UserCriteria);

		User user = userList.get(0);

		if (user != null) {
			// 构造权限
			// 将用户ID加入权限列表
			List<GrantedAuthority> authority = AuthorityUtils
					.commaSeparatedStringToAuthorityList(USER_ROLE + ","
							+ user.getId());

			// 将角色加入权限列表
			RoleResConditionEntity condition = new RoleResConditionEntity();
			condition.setDelFlg0(ComConst.DEL_FLG_0);
			condition.setRoleEnabled(ComConst.ENABLED_1001);
			List<String> roleList = systemUserRoleResExMapper
					.selectUserRole(condition);

			if (roleList != null) {
				for (String role : roleList) {
					authority.add(new SimpleGrantedAuthority(role));
				}
			}
			user.setAuthorities(authority);
		}

		return user;
	}

	/**
	 * <p>
	 * 描述：比较session中的验证码和用户输入的验证码是否相等
	 * </P>
	 * 
	 */
	protected void checkValidateCode() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();

		// @TODO 验证是否是通过Cookie登录，如果是通过Cookie登录则不验证验证码
		if (request.getParameter(usernameParameter) == null) {
			return;
		}

		String sessionValidateCode = obtainSessionValidateCode(request);
		String validateCodeParameter = obtainValidateCodeParameter(request);

		if (StringUtils.isEmpty(validateCodeParameter)
				|| !sessionValidateCode.equalsIgnoreCase(validateCodeParameter)) {
			throw new AuthenticationServiceException("验证码错误！");
		}
	}

	/**
	 * <p>
	 * 描述：获取用户输入的验证码.
	 * </P>
	 * 
	 * @param request
	 * @return String
	 */
	protected String obtainValidateCodeParameter(HttpServletRequest request) {
		return request.getParameter(validateCodeParameter);
	}

	/**
	 * <p>
	 * 描述：获取Session中的验证码.
	 * </P>
	 * 
	 * @param request
	 * @return String
	 */
	protected String obtainSessionValidateCode(HttpServletRequest request) {
		Object obj = request.getSession().getAttribute(sessionvalidateCodeKey);
		return null == obj ? "" : obj.toString();
	}

	/**
	 * <p>
	 * 描述：设置是否使用验证码
	 * </P>
	 * 
	 * @param useValidateCode
	 */
	@Autowired(required = false)
	@Qualifier("useValidateCode")
	public void setUseValidateCode(Boolean useValidateCode) {
		this.useValidateCode = useValidateCode;
	}

	/**
	 * <p>
	 * 描述：设置表单中存放用户输入验证码的参数名称
	 * </P>
	 * 
	 * @param validateCodeParameter
	 */
	@Autowired(required = false)
	@Qualifier("validateCodeParameter")
	public void setValidateCodeParameter(String validateCodeParameter) {
		this.validateCodeParameter = validateCodeParameter;
	}

	/**
	 * <p>
	 * 描述：设置Session中保存验证码的主键名称
	 * </P>
	 * 
	 * @param sessionvalidateCodeKey
	 */
	@Autowired(required = false)
	@Qualifier("sessionvalidateCodeKey")
	public void setSessionvalidateCodeKey(String sessionvalidateCodeKey) {
		UserDetailServiceEx.sessionvalidateCodeKey = sessionvalidateCodeKey;
	}

	/**
	 * <p>
	 * 描述：设置登录名参数名称
	 * </P>
	 * 
	 * @param sessionvalidateCodeKey
	 */
	@Autowired(required = false)
	@Qualifier("usernameParameter")
	public void setUsernameParameter(String usernameParameter) {
		this.usernameParameter = usernameParameter;
	}

}
