package com.ssmssm.view.home;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssmssm.core.view.BaseController;
import com.ssmssm.entity.system.User;
import com.ssmssm.service.system.UserService;

@Controller
public class HomeController extends BaseController
{
    
    @Autowired
    private UserService userService;
    
    @RequestMapping("/index")
    public String index()
    {
//        List<User> usersList = userService.findAll();
//        for (User user : usersList)
//        {
//            System.out.println(user);
//        }
//        User user = userService.findById("1");
//        System.err.println(user.getId());
        return "/index";
    }
    
}
