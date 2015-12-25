package com.ssmssm.service.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssmssm.core.utils.ComConst;
import com.ssmssm.dao.system.CommonCodeMapper;
import com.ssmssm.entity.system.CommonCode;
import com.ssmssm.entity.system.CommonCodeCriteria;

@Service("CommonCodeService")
public class CommonCodeService {

	@Autowired
	private CommonCodeMapper commonCodeMapper;

	/**
	 * <p>
	 * 描述：根据共通Code和共通明细Code取得共通Code信息
	 * </p>
	 * @param codeId
	 * @param codeDtlId
	 * @return
	 */
	public List<CommonCode> findByCodeIdAndCodeDtlId(Integer codeId,
			Short codeDtlId) {
		CommonCodeCriteria commonCodeCriteria = new CommonCodeCriteria();
		commonCodeCriteria.createCriteria()
				.andDelFlgEqualTo(ComConst.DEL_FLG_0).andCodeIdEqualTo(codeId)
				.andCodeDtlIdEqualTo(codeDtlId);
		return commonCodeMapper.selectByExample(commonCodeCriteria);
	}
	
	/**
	 * <p>
	 * 描述：根据共通Code取得共通Code信息
	 * </p>
	 * @param codeId
	 * @return
	 */
	public List<CommonCode> findByCodeId(Integer codeId) {
		CommonCodeCriteria commonCodeCriteria = new CommonCodeCriteria();
		commonCodeCriteria.createCriteria()
				.andDelFlgEqualTo(ComConst.DEL_FLG_0).andCodeIdEqualTo(codeId);
		return commonCodeMapper.selectByExample(commonCodeCriteria);
	}
}
