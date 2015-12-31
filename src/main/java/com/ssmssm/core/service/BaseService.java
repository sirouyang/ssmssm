package com.ssmssm.core.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import com.ssmssm.core.dao.BaseDao;
import com.ssmssm.core.entity.BaseCriteria;
import com.ssmssm.core.entity.BaseEntity;
import com.ssmssm.core.entity.Page;
import com.ssmssm.core.utils.ComConst;

@Transactional(readOnly = true)
public abstract class BaseService {
    
    /** 日志记录对象. */
    private Logger logger = Logger.getLogger(getClass());
    
    /** mapper(BaseDao):dao实例. */
    private BaseDao mapper;
    
    /**
     * 设置Mapper
     * @param mapper
     */
    public void setMapper(BaseDao mapper) {
        this.mapper = mapper;
    }
    
    /**
     * 获取Mapper
     * @return
     */
    protected BaseDao getMapper() {
        return mapper;
    }
    
    /**
     * 根据主键检索数据
     * @param key
     * @return
     */
    public Map<String, Object> selectByExamplePagination(Integer start, Integer length, BaseCriteria example) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        example.setPage(new Page(start, length));
        int cnt = getMapper().countByExample(example);
        resultMap.put("recordsTotal", cnt);
        resultMap.put("recordsFiltered", cnt);
        resultMap.put("data", getMapper().selectByExample(example));
        return resultMap;
    }

    /**
     * 根据条件检索数据
     * @param example
     * @return
     */
    public Map<String, Object> selectByExample(BaseCriteria example) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        int cnt = getMapper().countByExample(example);
        resultMap.put("recordsTotal", cnt);
        resultMap.put("recordsFiltered", cnt);
        resultMap.put("data", getMapper().selectByExample(example));
        return resultMap;
    }
    
    /**
     * 新增数据
     * @param record
     * @return
     */
    @Transactional
    protected Map<String, String> insert(BaseEntity entity){
        Map<String, String> resultMap = new HashMap<String, String>();
        Integer cnt = 0;
        try {
            cnt = getMapper().insert(entity);
        } catch (Exception e) {
            resultMap.put(ComConst.RESULT_CODE, ComConst.RESULT_CODE_FAILD_9);
            logger.error(e.getMessage());
        }
        if (cnt == 0) {
            resultMap.put(ComConst.RESULT_CODE, ComConst.RESULT_CODE_FAILD_1);
        } else if (cnt == 1) {
            resultMap.put(ComConst.RESULT_CODE, ComConst.RESULT_CODE_SUCCESS);
        } else {
            resultMap.put(ComConst.RESULT_CODE, ComConst.RESULT_CODE_FAILD_9);
        }
        return resultMap;
    }
    
    /**
     * 根据条件新增数据(null不处理)
     * @param record
     * @return
     */
    @Transactional
    protected Map<String, String> insertSelective(BaseEntity entity){
        Map<String, String> resultMap = new HashMap<String, String>();
        Integer cnt = 0;
        try {
            cnt = getMapper().insertSelective(entity);
        } catch (Exception e) {
            resultMap.put(ComConst.RESULT_CODE, ComConst.RESULT_CODE_FAILD_9);
            logger.error(e.getMessage());
        }
        if (cnt == 0) {
            resultMap.put(ComConst.RESULT_CODE, ComConst.RESULT_CODE_FAILD_1);
        } else if (cnt == 1) {
            resultMap.put(ComConst.RESULT_CODE, ComConst.RESULT_CODE_SUCCESS);
        } else {
            resultMap.put(ComConst.RESULT_CODE, ComConst.RESULT_CODE_FAILD_9);
        }
        return resultMap;
    }
    
    /**
     * 根据主键更新数据
     * @param record
     * @return
     */
    @Transactional
    public Map<String, String> updateByPrimaryKey(BaseEntity entity) {
        Map<String, String> resultMap = new HashMap<String, String>();
        Integer cnt = 0;
        try {
            cnt = getMapper().updateByPrimaryKey(entity);
        } catch (Exception e) {
            resultMap.put(ComConst.RESULT_CODE, ComConst.RESULT_CODE_FAILD_9);
            logger.error(e.getMessage());
        }
        if (cnt == 0) {
            resultMap.put(ComConst.RESULT_CODE, ComConst.RESULT_CODE_FAILD_2);
        } else if (cnt == 1) {
            resultMap.put(ComConst.RESULT_CODE, ComConst.RESULT_CODE_SUCCESS);
        } else {
            resultMap.put(ComConst.RESULT_CODE, ComConst.RESULT_CODE_FAILD_9);
        }
        return resultMap;
    }
    
    /**
     * 根据主键更新数据(null不处理)
     * @param record
     * @return
     */
    @Transactional
    public Map<String, String> updateByPrimaryKeySelective(BaseEntity entity) {
        Map<String, String> resultMap = new HashMap<String, String>();
        Integer cnt = 0;
        try {
            cnt = getMapper().updateByPrimaryKeySelective(entity);
        } catch (Exception e) {
            resultMap.put(ComConst.RESULT_CODE, ComConst.RESULT_CODE_FAILD_9);
            logger.error(e.getMessage());
        }
        if (cnt == 0) {
            resultMap.put(ComConst.RESULT_CODE, ComConst.RESULT_CODE_FAILD_2);
        } else if (cnt == 1) {
            resultMap.put(ComConst.RESULT_CODE, ComConst.RESULT_CODE_SUCCESS);
        } else {
            resultMap.put(ComConst.RESULT_CODE, ComConst.RESULT_CODE_FAILD_9);
        }
        return resultMap;
    }
    
    /**
     * 根据条件更新数据
     * @param record
     * @param example
     * @return
     */
    @Transactional
    public Map<String, String> updateByExample(BaseEntity record, BaseCriteria example) {
        Map<String, String> resultMap = new HashMap<String, String>();
        Integer cnt = 0;
        try {
            cnt = getMapper().updateByExample(record, example);
        } catch (Exception e) {
            resultMap.put(ComConst.RESULT_CODE, ComConst.RESULT_CODE_FAILD_9);
            logger.error(e.getMessage());
        }
        if (cnt == 0) {
            resultMap.put(ComConst.RESULT_CODE, ComConst.RESULT_CODE_FAILD_2);
        } else if (cnt == 1) {
            resultMap.put(ComConst.RESULT_CODE, ComConst.RESULT_CODE_SUCCESS);
        } else {
            resultMap.put(ComConst.RESULT_CODE, ComConst.RESULT_CODE_FAILD_9);
        }
        return resultMap;
    }
    
    /**
     * 根据条件更新数据(null不处理)
     * @param record
     * @param example
     * @return
     */
    @Transactional
    public Map<String, String> updateByExampleSelective(BaseEntity record, BaseCriteria example) {
        Map<String, String> resultMap = new HashMap<String, String>();
        Integer cnt = 0;
        try {
            cnt = getMapper().updateByExampleSelective(record, example);
        } catch (Exception e) {
            resultMap.put(ComConst.RESULT_CODE, ComConst.RESULT_CODE_FAILD_9);
            logger.error(e.getMessage());
        }
        if (cnt == 0) {
            resultMap.put(ComConst.RESULT_CODE, ComConst.RESULT_CODE_FAILD_2);
        } else if (cnt == 1) {
            resultMap.put(ComConst.RESULT_CODE, ComConst.RESULT_CODE_SUCCESS);
        } else {
            resultMap.put(ComConst.RESULT_CODE, ComConst.RESULT_CODE_FAILD_9);
        }
        return resultMap;
    }
    
    /**
     * 根据主键删除数据
     * @param key
     * @return
     */
    @Transactional
    public Map<String, String> deleteByPrimaryKey(BaseEntity entity) {
        Map<String, String> resultMap = new HashMap<String, String>();
        Integer cnt = 0;
        try {
            cnt = getMapper().deleteByPrimaryKey(entity);
        } catch (Exception e) {
            resultMap.put(ComConst.RESULT_CODE, ComConst.RESULT_CODE_FAILD_9);
            logger.error(e.getMessage());
        }
        if (cnt == 0) {
            resultMap.put(ComConst.RESULT_CODE, ComConst.RESULT_CODE_FAILD_2);
        } else if (cnt == 1) {
            resultMap.put(ComConst.RESULT_CODE, ComConst.RESULT_CODE_SUCCESS);
        } else {
            resultMap.put(ComConst.RESULT_CODE, ComConst.RESULT_CODE_FAILD_9);
        }
        return resultMap;
    }
    
    /**
     * 根据条件删除数据
     * @param example
     * @return
     */
    @Transactional
    public Map<String, String> deleteByExample(BaseCriteria example) {
        Map<String, String> resultMap = new HashMap<String, String>();
        Integer cnt = 0;
        try {
            cnt = getMapper().deleteByExample(example);
        } catch (Exception e) {
            resultMap.put(ComConst.RESULT_CODE, ComConst.RESULT_CODE_FAILD_9);
            logger.error(e.getMessage());
        }
        if (cnt == 0) {
            resultMap.put(ComConst.RESULT_CODE, ComConst.RESULT_CODE_FAILD_2);
        } else if (cnt == 1) {
            resultMap.put(ComConst.RESULT_CODE, ComConst.RESULT_CODE_SUCCESS);
        } else {
            resultMap.put(ComConst.RESULT_CODE, ComConst.RESULT_CODE_FAILD_9);
        }
        return resultMap;
    }
}
