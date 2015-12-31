package com.ssmssm.view.system;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.ssmssm.core.utils.ComConst;
import com.ssmssm.core.view.BaseController;
import com.ssmssm.entity.system.CommonCode;
import com.ssmssm.entity.system.User;
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
    
    /***
     * 新增用户
     * 
     * @return
     * @throws IOException
     * @throws JsonMappingException
     * @throws JsonParseException
     */
    @RequestMapping(value = "/user/insertUser", method = RequestMethod.POST)
    public @ResponseBody Map<String, String> insertUser(User user){
        return userService.insertUser(user);
    }
    
    @RequestMapping(value = "/user/getUserTypeList", method = RequestMethod.GET)
    public @ResponseBody List<CommonCode> getUserTypeList(Integer userType) {
        // request = ((ServletRequestAttributes) RequestContextHolder
        // .getRequestAttributes()).getRequest();
        return commonCodeService.findByCodeId(userType);
    }
}
