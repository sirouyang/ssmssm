package com.ssmssm.core.utils;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import ognl.OgnlOps;

import org.dozer.DozerBeanMapper;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;

public class BeanUtils {
    /** 持有Dozer单例, 避免重复创建DozerMapper消耗资源. */
    private static DozerBeanMapper dozer = new DozerBeanMapper();
    /** 持有Jackson单例, 避免重复创建ObjectMapper消耗资源. */
    private static ObjectMapper mapper;
    
    /**
     * 构造函数.
     */
    private BeanUtils() {}
    
    /**
     * 将实体对象转换为Json字符串.
     */
    public static String toJson(Object bean) throws JsonProcessingException {
        initJackson();
        
        if (bean == null) {
            return null;
        }
        
        return mapper.writeValueAsString(bean);
    }
    
    /**
     * 将Json字符串还原为实体对象.
     */
    public static <T> T toBean(String json, Class<T> beanClass) throws JsonParseException, JsonMappingException, IOException {
        initJackson();
        
        return mapper.readValue(json, beanClass);
    }
    
    /**
     * 初始化Jackson对象.
     */
    private static void initJackson() {
        if (mapper == null) {
            mapper = new ObjectMapper();
            mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
            mapper.setTimeZone(TimeZone.getDefault());
        }
    }
    
    /**
     * 基于Dozer转换对象的类型.
     */
    public static <T> T map(Object source, Class<T> destinationClass) {
        return dozer.map(source, destinationClass);
    }
    
    /**
     * 基于Dozer转换Collection中对象的类型.
     */
    public static <T> List<T> mapList(List<T> sourceList, Class<T> destinationClass) {
        if (sourceList == null || sourceList.isEmpty()) return sourceList;
        
        List<T> destinationList = Lists.newArrayList();
        for (Object sourceObject : sourceList) {
            T destinationObject = dozer.map(sourceObject, destinationClass);
            destinationList.add(destinationObject);
        }
        return destinationList;
    }

    /**
     * 基于Dozer将对象A的值拷贝到对象B中.
     */
    public static void copy(Object source, Object destinationObject) {
        dozer.map(source, destinationObject);
    }
    
    /**
     * 将<code>value</code>根据在<code>clazz</code>中的<code>propertyName</code>的数据类型进行转换.
     * @param clazz <code>propertyName对应的实体类
     * @param propertyName 属性名
     * @param value 要转换的值
     * @return <code>value</code>类型转换后的对象
     */
    @SuppressWarnings("rawtypes")
    public static Object convertValue (Class clazz, String propertyName, Object value) {
        try {
            int pos = propertyName.indexOf(".");
            
            if (pos != -1) {
                String p = propertyName.substring(0,pos);
                String s = propertyName.substring(pos+1);
                
                return convertValue(org.springframework.beans.BeanUtils.findPropertyType(p, new Class[]{clazz}),s,value);
            }
            
            Class toType = org.springframework.beans.BeanUtils.findPropertyType(propertyName, new Class[]{clazz});
            
            return convertValue(toType, value);
        } catch (Exception e) {
            throw new IllegalArgumentException("数据类型转换失败",e);
        }
    }
    
    /**
     * 将<code>value</code>根据在<code>valueType</code>中的数据类型进行转换.
     * @param valueType 数据类型
     * @param value 要转换的值
     * @return <code>value</code>类型转换后的对象
     * @throws Exception
     */
    public static Object convertValue(Class<?> valueType, Object value) throws Exception {
        if (valueType == Date.class) {
            return DateUtils.toDate(value);
        }
        
        return OgnlOps.convertValue(value, valueType);
    }
}
