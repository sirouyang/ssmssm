/**
 * <p>Copyright:Copyright(c) 2015</p>
 * <p>Company:上海中信信息发展股份有限公司</p>
 * <p>包名:com.ces.aiot.core.utils</p>
 * <p>文件名:ComConst.java</p>
 */
package com.ssmssm.core.utils;

/**
 * <p>
 * 描述:共通定数类
 * </p>
 * 
 */
public class ComConst {
	private volatile static ComConst comConst;

	private ComConst() {
	}

	public static ComConst getSingleton() {
		if (comConst == null) {
			synchronized (ComConst.class) {
				if (comConst == null) {
					comConst = new ComConst();
				}
			}
		}
		return comConst;
	}

	/** 系统用户CODE */
	public static final String SYSTEM_USER = "system";

	/** 默认系统最大日 */
	public static final String MAX_DATE = "2999/12/31";

	/** 半角空格 */
	public static final String HALF_SPACE = " ";

	/** 删除区分(0:有效) */
	public static final String DEL_FLG_0 = "0";

	/** 删除区分(1:无效) */
	public static final String DEL_FLG_1 = "1";

	/** 删除区分(R:资源) */
	public static final String DEL_FLG_R = "R";
	
    /** 性别(100001) */
    public static final Integer SEX_ID = 100001;

    /** 是否有效(100001)(1001:男) */
    public static final Short SEX_1001 = 1001;

    /** 是否有效(100001)(1002:女) */
    public static final Short SEX_1002 = 1002;
    
    /** 是否有效(100002) */
    public static final Integer ENABLED_ID = 100002;

	/** 是否有效(100002)(1001:有效) */
	public static final Short ENABLED_1001 = 1001;

	/** 是否有效(100002)(1002:无效) */
	public static final Short ENABLED_1002 = 1002;
	
    /** 是否自定义(100003) */
    public static final Integer USER_DEFINED_ID = 100003;

    /** 是否自定义(100003)(1001:自定义) */
    public static final Short USER_DEFINED_1001 = 1001;

    /** 是否自定义(100003)(1002:非自定义) */
    public static final Short USER_DEFINED_1002 = 1002;
    
    /** 用户类型(100004) */
    public static final Integer USER_TYPE_ID = 100004;
    
    /** 用户类型(100004)(1001:管理员) */
    public static final Short USER_TYPE_1001 = 1001;

}
