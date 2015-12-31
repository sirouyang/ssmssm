package com.ssmssm.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssmssm.core.entity.BaseCriteria;
import com.ssmssm.core.entity.BaseEntity;

public interface BaseDao {
    
    /**
     * 根据条件检索数据件数
     * @param example
     * @return
     */
    int countByExample(BaseCriteria example);
    
    /**
     * 根据主键检索数据
     * @param key
     * @return
     */
    BaseEntity selectByPrimaryKey(BaseEntity key);
    
    /**
     * 根据条件检索数据
     * @param example
     * @return
     */
    List<BaseEntity> selectByExample(BaseCriteria example);
    
    /**
     * 新增数据
     * @param record
     * @return
     */
    int insert(BaseEntity record);
    
    /**
     * 根据条件新增数据(null不处理)
     * @param record
     * @return
     */
    int insertSelective(BaseEntity record);
    
    /**
     * 根据主键更新数据
     * @param record
     * @return
     */
    int updateByPrimaryKey(BaseEntity record);
    
    /**
     * 根据主键更新数据(null不处理)
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(BaseEntity record);
    
    /**
     * 根据条件更新数据
     * @param record
     * @param example
     * @return
     */
    int updateByExample(@Param("record") BaseEntity record, @Param("example") BaseCriteria example);
    
    /**
     * 根据条件更新数据(null不处理)
     * @param record
     * @param example
     * @return
     */
    int updateByExampleSelective(@Param("record") BaseEntity record, @Param("example") BaseCriteria example);
    
    /**
     * 根据主键删除数据
     * @param key
     * @return
     */
    int deleteByPrimaryKey(BaseEntity key);
    
    /**
     * 根据条件删除数据
     * @param example
     * @return
     */
    int deleteByExample(BaseCriteria example);
    
}
