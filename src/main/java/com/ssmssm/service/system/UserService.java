package com.ssmssm.service.system;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.code.ssm.api.InvalidateAssignCache;
import com.google.code.ssm.api.ParameterValueKeyProvider;
import com.google.code.ssm.api.ReadThroughSingleCache;
import com.google.code.ssm.api.format.Serialization;
import com.google.code.ssm.api.format.SerializationType;
import com.ssmssm.core.utils.ComConst;
import com.ssmssm.dao.system.UserMapper;
import com.ssmssm.entity.Page;
import com.ssmssm.entity.system.User;
import com.ssmssm.entity.system.UserCriteria;
import com.ssmssm.entity.system.UserKey;

@Service("UserService")
public class UserService {
    
    @Autowired
    private UserMapper userMapper;
    
    /**
     * 检索所有的用户
     * @param start
     * @param length
     * @return
     */
    @Serialization(SerializationType.JSON)
    // @ReadThroughAssignCache(assignedKey = "userServicefindAll", namespace = "system", expiration = 3000)
    @ReadThroughSingleCache(namespace = "system.userList", expiration = 3000)
    public Map<String, Object> getUserListPagination(@ParameterValueKeyProvider(order = 0)
    final Integer start, @ParameterValueKeyProvider(order = 1)
    final Integer length) {
        System.err.println("没有缓存命中");
        Map<String, Object> resultMap = new HashMap<String, Object>();
        UserCriteria userCriteria = new UserCriteria();
        userCriteria.createCriteria().andDelFlgEqualTo(ComConst.DEL_FLG_0);
        userCriteria.setPage(new Page(start, length));
        int cnt = userMapper.countByExample(userCriteria);
        resultMap.put("recordsTotal", cnt);
        resultMap.put("recordsFiltered", cnt);
        resultMap.put("data", userMapper.selectByExample(userCriteria));
        return resultMap;
    }
    
    @Transactional
    @InvalidateAssignCache(namespace = "system.userList", assignedKey = "0/50")
    public Map<String, String> insertUser(User user) {
        Map<String, String> resultMap = new HashMap<String, String>();
        Integer cnt = userMapper.insert(user);
        if (cnt == 1) {
            resultMap.put(ComConst.RESULT_CODE, ComConst.RESULT_CODE_SUCCESS);
        } else {
            resultMap.put(ComConst.RESULT_CODE, ComConst.RESULT_CODE_FAILD_9);
        }
        return resultMap;
    }
    
    /**
     * 
     * @param userId
     * @return
     */
    
    @Serialization(SerializationType.JSON)
    @ReadThroughSingleCache(namespace = "system", expiration = 3000)
    public User findById(@ParameterValueKeyProvider
    final String userId) {
        System.err.println("没有缓存命中");
        UserKey key = new UserKey();
        key.setId(userId);
        User user = userMapper.selectByPrimaryKey(key);
        
        return user;
    }
}
