package com.pawan.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pawan.boot.service.UserAccountService;

@Controller
public class ForgotPasswordController {

	@Autowired
	private UserAccountService service;
	@GetMapping("/loadForgotPwdForm")
	public String loadForgotPwdForm() {
		
		return "forgotpwd";
	}
	
	
	@PostMapping("/forgotPwd")
	public String handleForgotSubmitBtn(@RequestParam("email") String email,Model model) {
	 
	String status=	service.recoverPwd(email);
	  if(status.equals("password sent to email")) {
		  
		  model.addAttribute("successMsg", status);
	  }
	  else {
		  
		  model.addAttribute("errorMsg", status);
	  }
		return "forgotpwd";
	}
}
