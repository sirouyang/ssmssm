package com.ssmssm.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssmssm.core.utils.ComConst;

public class BaseEntity implements Serializable {

	public BaseEntity() {
		Date nowDate = new Date();
		this.delFlg = ComConst.DEL_FLG_0;
		this.sysRegUsrCd = ComConst.SYSTEM_USER;
		this.sysRegTmsp = nowDate;
		this.sysUpdUsrCd = ComConst.SYSTEM_USER;
		this.sysUpdTmsp = nowDate;
	}

	public BaseEntity(String userId) {
		Date nowDate = new Date();
		this.delFlg = ComConst.DEL_FLG_0;
		this.sysRegUsrCd = userId;
		this.sysRegTmsp = nowDate;
		this.sysUpdUsrCd = userId;
		this.sysUpdTmsp = nowDate;
	}

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column T_AIOT_RESOURCE.SYSTEM_RESERVE1
	 * 
	 * @mbggenerated
	 */
	private String systemReserve1;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column T_AIOT_RESOURCE.SYSTEM_RESERVE2
	 * 
	 * @mbggenerated
	 */
	private String systemReserve2;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column T_AIOT_RESOURCE.SYSTEM_RESERVE3
	 * 
	 * @mbggenerated
	 */
	private String systemReserve3;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column T_AIOT_RESOURCE.SYSTEM_RESERVE4
	 * 
	 * @mbggenerated
	 */
	private String systemReserve4;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column T_AIOT_RESOURCE.SYSTEM_RESERVE5
	 * 
	 * @mbggenerated
	 */
	private String systemReserve5;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column T_AIOT_RESOURCE.DEL_FLG
	 * 
	 * @mbggenerated
	 */
	private String delFlg;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column T_AIOT_RESOURCE.SYS_REG_TMSP
	 * 
	 * @mbggenerated
	 */
	private Date sysRegTmsp;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column T_AIOT_RESOURCE.SYS_REG_USR_CD
	 * 
	 * @mbggenerated
	 */
	private String sysRegUsrCd;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column T_AIOT_RESOURCE.SYS_UPD_TMSP
	 * 
	 * @mbggenerated
	 */
	private Date sysUpdTmsp;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column T_AIOT_RESOURCE.SYS_UPD_USR_CD
	 * 
	 * @mbggenerated
	 */
	private String sysUpdUsrCd;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database table T_AIOT_RESOURCE
	 * 
	 * @mbggenerated
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column T_AIOT_RESOURCE.SYSTEM_RESERVE1
	 * 
	 * @return the value of T_AIOT_RESOURCE.SYSTEM_RESERVE1
	 * @mbggenerated
	 */
	public String getSystemReserve1() {
		return systemReserve1;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column T_AIOT_RESOURCE.SYSTEM_RESERVE1
	 * 
	 * @param systemReserve1
	 *            the value for T_AIOT_RESOURCE.SYSTEM_RESERVE1
	 * @mbggenerated
	 */
	public void setSystemReserve1(String systemReserve1) {
		this.systemReserve1 = systemReserve1 == null ? null : systemReserve1
				.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column T_AIOT_RESOURCE.SYSTEM_RESERVE2
	 * 
	 * @return the value of T_AIOT_RESOURCE.SYSTEM_RESERVE2
	 * @mbggenerated
	 */
	public String getSystemReserve2() {
		return systemReserve2;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column T_AIOT_RESOURCE.SYSTEM_RESERVE2
	 * 
	 * @param systemReserve2
	 *            the value for T_AIOT_RESOURCE.SYSTEM_RESERVE2
	 * @mbggenerated
	 */
	public void setSystemReserve2(String systemReserve2) {
		this.systemReserve2 = systemReserve2 == null ? null : systemReserve2
				.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column T_AIOT_RESOURCE.SYSTEM_RESERVE3
	 * 
	 * @return the value of T_AIOT_RESOURCE.SYSTEM_RESERVE3
	 * @mbggenerated
	 */
	public String getSystemReserve3() {
		return systemReserve3;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column T_AIOT_RESOURCE.SYSTEM_RESERVE3
	 * 
	 * @param systemReserve3
	 *            the value for T_AIOT_RESOURCE.SYSTEM_RESERVE3
	 * @mbggenerated
	 */
	public void setSystemReserve3(String systemReserve3) {
		this.systemReserve3 = systemReserve3 == null ? null : systemReserve3
				.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column T_AIOT_RESOURCE.SYSTEM_RESERVE4
	 * 
	 * @return the value of T_AIOT_RESOURCE.SYSTEM_RESERVE4
	 * @mbggenerated
	 */
	public String getSystemReserve4() {
		return systemReserve4;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column T_AIOT_RESOURCE.SYSTEM_RESERVE4
	 * 
	 * @param systemReserve4
	 *            the value for T_AIOT_RESOURCE.SYSTEM_RESERVE4
	 * @mbggenerated
	 */
	public void setSystemReserve4(String systemReserve4) {
		this.systemReserve4 = systemReserve4 == null ? null : systemReserve4
				.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column T_AIOT_RESOURCE.SYSTEM_RESERVE5
	 * 
	 * @return the value of T_AIOT_RESOURCE.SYSTEM_RESERVE5
	 * @mbggenerated
	 */
	public String getSystemReserve5() {
		return systemReserve5;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column T_AIOT_RESOURCE.SYSTEM_RESERVE5
	 * 
	 * @param systemReserve5
	 *            the value for T_AIOT_RESOURCE.SYSTEM_RESERVE5
	 * @mbggenerated
	 */
	public void setSystemReserve5(String systemReserve5) {
		this.systemReserve5 = systemReserve5 == null ? null : systemReserve5
				.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column T_AIOT_RESOURCE.DEL_FLG
	 * 
	 * @return the value of T_AIOT_RESOURCE.DEL_FLG
	 * @mbggenerated
	 */
	public String getDelFlg() {
		return delFlg;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column T_AIOT_RESOURCE.DEL_FLG
	 * 
	 * @param delFlg
	 *            the value for T_AIOT_RESOURCE.DEL_FLG
	 * @mbggenerated
	 */
	public void setDelFlg(String delFlg) {
		this.delFlg = delFlg == null ? null : delFlg.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column T_AIOT_RESOURCE.SYS_REG_TMSP
	 * 
	 * @return the value of T_AIOT_RESOURCE.SYS_REG_TMSP
	 * @mbggenerated
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSSSSS")
	public Date getSysRegTmsp() {
		return sysRegTmsp;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column T_AIOT_RESOURCE.SYS_REG_TMSP
	 * 
	 * @param sysRegTmsp
	 *            the value for T_AIOT_RESOURCE.SYS_REG_TMSP
	 * @mbggenerated
	 */
	public void setSysRegTmsp(Date sysRegTmsp) {
		this.sysRegTmsp = sysRegTmsp;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column T_AIOT_RESOURCE.SYS_REG_USR_CD
	 * 
	 * @return the value of T_AIOT_RESOURCE.SYS_REG_USR_CD
	 * @mbggenerated
	 */
	public String getSysRegUsrCd() {
		return sysRegUsrCd;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column T_AIOT_RESOURCE.SYS_REG_USR_CD
	 * 
	 * @param sysRegUsrCd
	 *            the value for T_AIOT_RESOURCE.SYS_REG_USR_CD
	 * @mbggenerated
	 */
	public void setSysRegUsrCd(String sysRegUsrCd) {
		this.sysRegUsrCd = sysRegUsrCd == null ? null : sysRegUsrCd.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column T_AIOT_RESOURCE.SYS_UPD_TMSP
	 * 
	 * @return the value of T_AIOT_RESOURCE.SYS_UPD_TMSP
	 * @mbggenerated
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSSSSS")
	public Date getSysUpdTmsp() {
		return sysUpdTmsp;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column T_AIOT_RESOURCE.SYS_UPD_TMSP
	 * 
	 * @param sysUpdTmsp
	 *            the value for T_AIOT_RESOURCE.SYS_UPD_TMSP
	 * @mbggenerated
	 */
	public void setSysUpdTmsp(Date sysUpdTmsp) {
		this.sysUpdTmsp = sysUpdTmsp;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column T_AIOT_RESOURCE.SYS_UPD_USR_CD
	 * 
	 * @return the value of T_AIOT_RESOURCE.SYS_UPD_USR_CD
	 * @mbggenerated
	 */
	public String getSysUpdUsrCd() {
		return sysUpdUsrCd;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column T_AIOT_RESOURCE.SYS_UPD_USR_CD
	 * 
	 * @param sysUpdUsrCd
	 *            the value for T_AIOT_RESOURCE.SYS_UPD_USR_CD
	 * @mbggenerated
	 */
	public void setSysUpdUsrCd(String sysUpdUsrCd) {
		this.sysUpdUsrCd = sysUpdUsrCd == null ? null : sysUpdUsrCd.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table T_AIOT_RESOURCE
	 * 
	 * @mbggenerated
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", systemReserve1=").append(systemReserve1);
		sb.append(", systemReserve2=").append(systemReserve2);
		sb.append(", systemReserve3=").append(systemReserve3);
		sb.append(", systemReserve4=").append(systemReserve4);
		sb.append(", systemReserve5=").append(systemReserve5);
		sb.append(", delFlg=").append(delFlg);
		sb.append(", sysRegTmsp=").append(sysRegTmsp);
		sb.append(", sysRegUsrCd=").append(sysRegUsrCd);
		sb.append(", sysUpdTmsp=").append(sysUpdTmsp);
		sb.append(", sysUpdUsrCd=").append(sysUpdUsrCd);
		sb.append("]");
		return sb.toString();
	}
}