package com.ssmssm.service.system;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssmssm.core.service.BaseService;
import com.ssmssm.core.utils.ComConst;
import com.ssmssm.core.utils.EncodeUtils;
import com.ssmssm.dao.system.UserMapper;
import com.ssmssm.entity.system.User;
import com.ssmssm.entity.system.UserCriteria;

@Service("UserService")
public class UserService extends BaseService {
    
    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    public void setMapper(UserMapper dao) {
        super.setMapper(dao);
    }
    
    /**
     * 检索所有的用户
     * 
     * @param start
     * @param length
     * @return
     */
    // @Serialization(SerializationType.JSON)
    // @ReadThroughSingleCache(namespace = "system.userList", expiration = 3000)
    // public Map<String, Object> getUserListPagination(@ParameterValueKeyProvider(order = 0)
    // final Integer start, @ParameterValueKeyProvider(order = 1)
    // final Integer length) {
    public Map<String, Object> getUserListPagination(Integer start, Integer length) {
        UserCriteria userCriteria = new UserCriteria();
        userCriteria.createCriteria().andDelFlgEqualTo(ComConst.DEL_FLG_0);
        return super.selectByExamplePagination(start, length, userCriteria);
    }
    
    /**
     * 新增用户
     * 
     * @param user
     * @return
     */
    @Transactional
    // @InvalidateAssignCache(namespace = "system.userList", assignedKey = "0/50")
    public Map<String, String> insertUser(User user) {
        user.setUserPsw(EncodeUtils.MD5(user.getUserPsw(), user.getUserName()));
        return super.insert(user);
    }
    
    /**
     * 编辑用户
     * 
     * @param user
     * @return
     */
    @Transactional
    public Map<String, String> updateUser(User user) {
        user.setUserPsw(EncodeUtils.MD5(user.getUserPsw(), user.getUserName()));
        return super.updateByPrimaryKey(user);
    }
    
    /**
     * 删除用户
     * 
     * @param user
     * @return
     */
    @Transactional
    public Map<String, String> deleteUser(User user) {
        return super.deleteByPrimaryKey(user);
    }
    
    // /**
    // *
    // * @param userId
    // * @return
    // */
    //
    // @Serialization(SerializationType.JSON)
    // @ReadThroughSingleCache(namespace = "system", expiration = 3000)
    // public User findById(@ParameterValueKeyProvider
    // final String userId) {
    // System.err.println("没有缓存命中");
    // UserKey key = new UserKey();
    // key.setId(userId);
    // User user = userMapper.selectByPrimaryKey(key);
    //
    // return user;
    // }
}
