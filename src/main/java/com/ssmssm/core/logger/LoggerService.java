package com.ssmssm.core.logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssmssm.dao.system.BusinessLogsMapper;
import com.ssmssm.entity.system.BusinessLogs;

/**
 * 日志记录服务类.
 * <p>
 * 描述:负责操作日志的管理
 * </p>
 */
@Service("LoggerService")
public class LoggerService {

	@Autowired
	private BusinessLogsMapper businessLogsMapper;

	/**
	 * 保存实体.
	 * 
	 * @param entity
	 *            要保存的实体
	 */
	@Transactional
	public int save(BusinessLogs entity) {
		return businessLogsMapper.insert(entity);
	};
}
