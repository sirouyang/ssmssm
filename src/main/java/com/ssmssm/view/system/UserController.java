package com.ssmssm.view.system;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssmssm.core.view.BaseController;
import com.ssmssm.entity.system.CommonCode;
import com.ssmssm.service.system.CommonCodeService;
import com.ssmssm.service.system.UserService;

@Controller
public class UserController extends BaseController {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private CommonCodeService commonCodeService;
    
    @RequestMapping("/user/index")
    public String index() {
        return "/user/index";
    }
    
    /***
     * 取得所有用户
     * 
     * @return
     */
    @RequestMapping(value = "/user/getUserListPagination", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> getUserListPagination(@RequestParam("draw") Integer draw,
        @RequestParam("start") Integer start, @RequestParam("length") Integer length) {
        Map<String, Object> resultMap = userService.getUserListPagination(start, length);
        resultMap.put("draw", draw);
        return resultMap;
    }
    
    @RequestMapping(value = "/user/getUserTypeList", method = RequestMethod.POST)
    public @ResponseBody List<CommonCode> getUserTypeList(Integer userType) {
        // request = ((ServletRequestAttributes) RequestContextHolder
        // .getRequestAttributes()).getRequest();
        return commonCodeService.findByCodeId(userType);
    }
}
