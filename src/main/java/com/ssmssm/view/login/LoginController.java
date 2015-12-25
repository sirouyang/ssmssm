package com.ssmssm.view.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssmssm.core.view.BaseController;

@Controller
public class LoginController extends BaseController {

	@RequestMapping("/login")
	public String login() {
		return "/login/login";
	}
}
