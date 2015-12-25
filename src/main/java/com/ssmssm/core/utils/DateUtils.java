package com.ssmssm.core.utils;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

public class DateUtils {
    /** DATETIME_PATTERN(String):yyyy-MM-dd HH:mm:ss. */
    public static final String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
      
    /** DATE_PATTERN(String):yyyy-MM-dd. */
    public static final String DATE_PATTERN = "yyyy-MM-dd";
      
    /** MONTH_PATTERN(String):yyyy-MM. */
    public static final String MONTH_PATTERN = "yyyy-MM";
    
    /**
     * 构造函数.
     * @author Reamy(杨木江 yangmujiang@sohu.com)
     * @date 2013-06-05  14:22:33
     */
    private DateUtils() {}
    
    /**
     * 转换为java.util.Date对象.
     * @param value 带转换对象
     * @return 对应的Date对象
     * @author Reamy(杨木江 yangmujiang@sohu.com)
     * @throws Exception 
     * @date 2013-04-17  08:42:05
     */
    public static final Date toDate(Object value) throws Exception {
        Date result = null;
          
        if (value instanceof String) {
            if (StringUtils.isNotEmpty((String)value)) {
                result = org.apache.commons.lang3.time.DateUtils.parseDate((String) value, new String[] { DATE_PATTERN, DATETIME_PATTERN, MONTH_PATTERN });
                
                if (result == null && StringUtils.isNotEmpty((String)value)) {
                    try {
                        result = new Date(new Long((String) value).longValue());
                    } catch (Exception e) {
                        throw e;
                    }
                }
            }
        } else if (value instanceof Object[]) {
            Object[] array = (Object[]) value;
            
            if ((array != null) && (array.length >= 1)) {
                value = array[0];
                result = toDate(value);
            }
        } else if (Date.class.isAssignableFrom(value.getClass())) {
            result = (Date) value;
        }
        
        return result;
    }
    
    /**
     * 转换为字符串.
     * @author Reamy(杨木江 yangmujiang@sohu.com)
     * @date 2013-06-18  18:04:48
     */
    public static final String toString(Date date, String pattern) {
        String result = null;
        
        if (date != null) {
            result = DateFormatUtils.format(date, pattern);
        }
        
        return result;
    }
}
