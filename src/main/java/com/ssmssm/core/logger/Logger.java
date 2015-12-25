package com.ssmssm.core.logger;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 日志记录管理注解.
 * <p>描述:定义操作日志注解可用属性及其定义</p>
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Logger {
	/** 模块名称 */
	String modelName() default "";
	
	/** 操作类型 */
	String actionType() default "";
	
	/** 操作内容 */
	String logger() default "";
	
	/** 操作成功 */
	String opSucess() default "成功";
	
	/** 操作失败 */
	String opError() default "失败";
	
	/** 是否记录日志，默认为记录日志  */
	boolean log() default true;
	
	/** 整个类均不进行日志记录，默认记录  */
	boolean globaldisable() default false;
}
