package com.ssmssm.dao.system;

import com.ssmssm.core.annotation.MyBatisRepository;
import com.ssmssm.core.dao.BaseDao;
import com.ssmssm.entity.system.BusinessLogs;
import com.ssmssm.entity.system.BusinessLogsCriteria;
import com.ssmssm.entity.system.BusinessLogsKey;

import java.util.List;

import org.apache.ibatis.annotations.Param;

@MyBatisRepository
public interface BusinessLogsMapper  extends BaseDao{

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table t_sys_business_logs
     * @mbggenerated
     */
    int countByExample(BusinessLogsCriteria example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table t_sys_business_logs
     * @mbggenerated
     */
    int deleteByExample(BusinessLogsCriteria example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table t_sys_business_logs
     * @mbggenerated
     */
    int deleteByPrimaryKey(BusinessLogsKey key);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table t_sys_business_logs
     * @mbggenerated
     */
    int insert(BusinessLogs record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table t_sys_business_logs
     * @mbggenerated
     */
    int insertSelective(BusinessLogs record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table t_sys_business_logs
     * @mbggenerated
     */
    List<BusinessLogs> selectByExample(BusinessLogsCriteria example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table t_sys_business_logs
     * @mbggenerated
     */
    BusinessLogs selectByPrimaryKey(BusinessLogsKey key);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table t_sys_business_logs
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") BusinessLogs record, @Param("example") BusinessLogsCriteria example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table t_sys_business_logs
     * @mbggenerated
     */
    int updateByExample(@Param("record") BusinessLogs record, @Param("example") BusinessLogsCriteria example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table t_sys_business_logs
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(BusinessLogs record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table t_sys_business_logs
     * @mbggenerated
     */
    int updateByPrimaryKey(BusinessLogs record);
}