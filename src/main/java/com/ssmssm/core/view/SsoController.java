package com.ssmssm.core.view;

import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.codec.Utf8;
import org.springframework.security.web.authentication.rememberme.InvalidCookieException;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.ssmssm.core.utils.TokenUtils;
import com.ssmssm.entity.system.User;

public class SsoController {
    
    @Autowired
    @Qualifier("XarchUserService")
    private UserDetailsService userDetailsService;
    
    private HttpServletRequest request;
    private HttpServletResponse response;
    //private HttpSession session;
    
    @ModelAttribute
    private void setReqAndResp(HttpServletRequest request,HttpServletResponse response) {
        this.request = request;
        this.response = response;
        //this.session = request.getSession(true); //false:如果有则返回，否则返回NULL
    }
    
    /**
     * sso.
     * 
     * @return
     */
    public Object sso()
        throws Exception
    {
        String tokenStr = obtainTokenParameter();
        String[] tokens = TokenUtils.decodeToken(tokenStr);
        User user = loadSysUser(tokens);
        if (user != null)
        {
            // long expiryTime = new Long(tokens[1]).longValue();
            setCookie(rememberMeCookieName, tokenStr, tokenLifetime);
            setCookie(LOGINUSERNAME_COOKIEKEY, URLEncoder.encode((user.getFullName()), "UTF-8"), -1);
        }
        return tokenStr;
    }
    
    /**
     * <p>
     * 创建Cookie
     * </p>
     * 
     * @param name
     * @param value
     * @param maxAge
     */
    private void setCookie(String name, String value, int maxAge)
    {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(maxAge);
        cookie.setPath(getCookiePath(getRequest()));
        
        if (useSecureCookie == null)
        {
            cookie.setSecure(getRequest().isSecure());
        }
        else
        {
            cookie.setSecure(useSecureCookie);
        }
        
        response.addCookie(cookie);
        
    }
    
    private String getCookiePath(HttpServletRequest request)
    {
        String contextPath = request.getContextPath();
        return contextPath.length() > 0 ? contextPath : "/";
    }
    
    /**
     * <p>
     * 获取用户信息.
     * </p>
     * 
     * @param tokens
     * @return
     * @throws Exception
     */
    private User loadSysUser(String[] tokens)
        throws Exception
    {
        if (tokens.length != 3)
        {
            throw new InvalidCookieException("Cookie token did not contain 3" + " tokens, but contained '"
                + Arrays.asList(tokens) + "'");
        }
        
        long tokenExpiryTime;
        
        try
        {
            tokenExpiryTime = new Long(tokens[1]).longValue();
        }
        catch (NumberFormatException nfe)
        {
            throw new InvalidCookieException("Cookie token[1] did not contain a valid number (contained '" + tokens[1]
                + "')");
        }
        
        if (isTokenExpired(tokenExpiryTime))
        {
            throw new InvalidCookieException("Cookie token[1] has expired (expired on '" + new Date(tokenExpiryTime)
                + "'; current time is '" + new Date() + "')");
        }
        
        User user = (User)userDetailsService.loadUserByUsername(tokens[0]);
        
        String expectedTokenSignature =
            TokenUtils.makeTokenSignature(tokenExpiryTime, user.getUsername(), user.getPassword());
        
        if (!equals(expectedTokenSignature, tokens[2]))
        {
            throw new InvalidCookieException("Cookie token[2] contained signature '" + tokens[2] + "' but expected '"
                + expectedTokenSignature + "'");
        }
        
        return user;
    }
    
    protected boolean isTokenExpired(long tokenExpiryTime)
    {
        return tokenExpiryTime < System.currentTimeMillis();
    }
    
    private static boolean equals(String expected, String actual)
    {
        byte[] expectedBytes = bytesUtf8(expected);
        byte[] actualBytes = bytesUtf8(actual);
        if (expectedBytes.length != actualBytes.length)
        {
            return false;
        }
        
        int result = 0;
        for (int i = 0; i < expectedBytes.length; i++)
        {
            result |= expectedBytes[i] ^ actualBytes[i];
        }
        return result == 0;
    }
    
    private static byte[] bytesUtf8(String s)
    {
        if (s == null)
        {
            return null;
        }
        return Utf8.encode(s);
    }
    
    private String obtainTokenParameter()
    {
        return (String)getRequest().getParameter(SSO_TOKEN);
    }
    
    private HttpServletRequest getRequest()
    {
        return request;
    }
    
    private static final String SSO_TOKEN = "_token";
    
    public static final String SPRING_SECURITY_REMEMBER_ME_COOKIE_KEY = "SPRING_SECURITY_REMEMBER_ME_COOKIE";
    
    private static final String LOGINUSERNAME_COOKIEKEY = "_xarch_loginuser_name_";
    
    private static final int tokenLifetime = 30 * 60;
    
    private Boolean useSecureCookie = null;
    
    private String rememberMeCookieName = SPRING_SECURITY_REMEMBER_ME_COOKIE_KEY;
    
    public User getModel()
    {
        return new User();
    }
}
