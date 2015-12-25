package com.ssmssm.core.utils;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.crypto.codec.Hex;


/**
 * 单点登录令牌工具类.
 */
public class TokenUtils {
    
    private static final String key = "ssoKey";
    private static final String DELIMITER = ":";
    
    
    public static String makeTokenSignature(long tokenExpiryTime, String username,
            String password) {
        String data = username + ":" + tokenExpiryTime + ":" + password + ":" + key;
        
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("No MD5 algorithm available!");
        }
        return new String(Hex.encode(digest.digest(data.getBytes())));
        
    }
    
    public static String encodeToken(String[] cookieTokens) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cookieTokens.length; i++) {
            sb.append(cookieTokens[i]);

            if (i < cookieTokens.length - 1) {
                sb.append(DELIMITER);
            }
        }

        String value = sb.toString();

        sb = new StringBuilder(new String(Base64.encode(value.getBytes())));

        while (sb.charAt(sb.length() - 1) == '=') {
            sb.deleteCharAt(sb.length() - 1);
        }

        return sb.toString();
    }
    
    public static String[] decodeToken(String cookieValue) throws IOException{
        for (int j = 0; j < cookieValue.length() % 4; j++) {
            cookieValue = cookieValue + "=";
        }
        
        String cookieAsPlainText = new String(Base64.decode(cookieValue.getBytes()));
        String[] tokens = org.springframework.util.StringUtils.delimitedListToStringArray(cookieAsPlainText, DELIMITER);

        if ((tokens[0].equalsIgnoreCase("http") || tokens[0].equalsIgnoreCase("https")) && tokens[1].startsWith("//")) {
            String[] newTokens = new String[tokens.length - 1];
            newTokens[0] = tokens[0] + ":" + tokens[1];
            System.arraycopy(tokens, 2, newTokens, 1, newTokens.length - 1);
            tokens = newTokens;
        }

        return tokens;
    }
    
    /**
     * <p>根据用户名，密码，和过期时间生成Token，密码password为加密后的密码，expiryTime过期时间，单位为分钟</p>.
     * @param loginname 用户名
     * @param password  密码
     * @param expiryTime 过期时间
     * @return 令牌字符串
     */
    public static String getToken(String loginname,String password,int expiryTime){
        assert loginname!=null&&!"".equals(loginname.trim()):"loginname 不能为空！";
        assert password!=null&&!"".equals(password.trim()):"password 不能为空！";
        
        long time = System.currentTimeMillis() + expiryTime*60*1000L;
        return encodeToken(new String[]{loginname,Long.toString(time),makeTokenSignature(time,loginname,password)});
        
    }
    /**
     * <p>根据用户名，密码生成Token，密码password为加密后的密码，Token过期时间为30分钟</p>.
     * @param loginname 用户名
     * @param password 密码
     * @return 令牌字符串
     */
    public static String getToken(String loginname,String password){
        return getToken(loginname,password,30);
    }
}
