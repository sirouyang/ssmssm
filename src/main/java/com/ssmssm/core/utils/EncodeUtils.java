package com.ssmssm.core.utils;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

/**
 * 加密处理辅助工具类.
 * <p>
 * 描述:封装常用的加密处理函数
 * </p>
 */
public class EncodeUtils
{
    /**
     * 采用MD5进行加密.
     * 
     * @param rawPass 明文密码
     * @param salt 一点盐
     * @return 密文密码
     */
    public static String MD5(String rawPass, Object salt)
    {
        return new Md5PasswordEncoder().encodePassword(rawPass, salt);
    }
    
    /**
     * 采用MD5进行加密.
     * 
     * @param rawPass 明文密码
     * @return 密文密码
     */
    public static String MD5(String rawPass)
    {
        return MD5(rawPass, null);
    }
}