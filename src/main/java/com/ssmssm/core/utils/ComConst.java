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

	/** 是否有效(900003)(1001:有效) */
	public static final Short ENABLED_1001 = 1001;

	/** 是否有效(900003)(1002:无效) */
	public static final Short ENABLED_1002 = 1002;

}
