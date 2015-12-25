package com.ssmssm.daoEx.system;

import java.util.List;

import com.ssmssm.core.annotation.MyBatisRepository;
import com.ssmssm.entityEx.system.RoleResConditionEntity;
import com.ssmssm.entityEx.system.RoleResResultEntity;

@MyBatisRepository
public interface UserRoleResExMapper {

	List<RoleResResultEntity> selectRoleRes(RoleResConditionEntity condition);

	List<String> selectUserRole(RoleResConditionEntity condition);
}