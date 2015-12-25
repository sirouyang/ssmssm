package com.ssmssm.dao.system;

import com.ssmssm.core.annotation.MyBatisRepository;
import com.ssmssm.entity.system.Resource;
import com.ssmssm.entity.system.ResourceCriteria;
import com.ssmssm.entity.system.ResourceKey;

import java.util.List;

import org.apache.ibatis.annotations.Param;

@MyBatisRepository
public interface ResourceMapper {

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table t_auth_resource
     * @mbggenerated
     */
    int countByExample(ResourceCriteria example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table t_auth_resource
     * @mbggenerated
     */
    int deleteByExample(ResourceCriteria example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table t_auth_resource
     * @mbggenerated
     */
    int deleteByPrimaryKey(ResourceKey key);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table t_auth_resource
     * @mbggenerated
     */
    int insert(Resource record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table t_auth_resource
     * @mbggenerated
     */
    int insertSelective(Resource record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table t_auth_resource
     * @mbggenerated
     */
    List<Resource> selectByExample(ResourceCriteria example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table t_auth_resource
     * @mbggenerated
     */
    Resource selectByPrimaryKey(ResourceKey key);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table t_auth_resource
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") Resource record, @Param("example") ResourceCriteria example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table t_auth_resource
     * @mbggenerated
     */
    int updateByExample(@Param("record") Resource record, @Param("example") ResourceCriteria example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table t_auth_resource
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Resource record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table t_auth_resource
     * @mbggenerated
     */
    int updateByPrimaryKey(Resource record);
}