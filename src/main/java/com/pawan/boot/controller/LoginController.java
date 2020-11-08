package com.pawan.boot.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.pawan.boot.service.UserAccountService;

@Controller
public class LoginController {

	@Autowired
	private UserAccountService service;
	/**
	 * this method is used to load the login page
	 * @return
	 */
	@GetMapping("/index")

	public String index() {

		return "index";
	}

	/**
	 * this method is used to handle sigin btn request
	 * return view name
	 * @param request
	 * @param model
	 * @return
	 */
	@PostMapping(value= "/login")
	
	public String handleSiginBtn(HttpServletRequest request ,Model model) {
		String viewName = "";
		String email=request.getParameter("email");
		String pwd=request.getParameter("pwd");
		String status=service.loginCheck(email, pwd);
		
		if(status.equals("valid")) {
			viewName="dashboard";
		}
		else {
			viewName="index";
			model.addAttribute("errMsg", status);
		}
		
		return viewName;
	}
}
