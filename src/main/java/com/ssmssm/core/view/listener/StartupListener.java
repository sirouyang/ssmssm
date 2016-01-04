package com.ssmssm.core.view.listener;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import com.ssmssm.dao.system.CommonCodeMapper;
import com.ssmssm.entity.system.CommonCode;
import com.ssmssm.entity.system.CommonCodeCriteria;

@Service
public class StartupListener implements ApplicationContextAware {
    
    protected Logger logger = LoggerFactory.getLogger(StartupListener.class);
    
    public static List<CommonCode> commonCodeList = new ArrayList<CommonCode>();
    
    @Autowired
    private CommonCodeMapper commonCodeMapper;
    
    @Override
    public void setApplicationContext(ApplicationContext ctx)
        throws BeansException {
        
        commonCodeList = commonCodeMapper.selectByExample(new CommonCodeCriteria());
        logger.debug(commonCodeList.toString());
        
    }
}
