package com.mh.springweblearning.controller;

import com.mh.springweblearning.model.Login;
import com.mh.springweblearning.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("name")
public class WelcomeController {

    /*
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String showLogin(ModelMap model) {
		model.addAttribute("login", new Login());
		return "login";
	}
    */

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showWelcomePage(ModelMap model) {
		SecurityContext ctx = SecurityContextHolder.getContext();
		System.out.println("username : " + ctx.getAuthentication().getName());
		model.addAttribute("name", ctx.getAuthentication().getName());
		return "welcome";
	}

/*
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String submitLoginInfo(ModelMap map, @RequestParam(required = false) String user, @RequestParam(required = false) String password) {
		if(loginService.validateUser(user, password)) {
			map.addAttribute("name",user);
			return "welcome";
		} else {
			map.addAttribute("errorMessage","Invalid Credentials");
			return "login";
		}

	}
	*/
}