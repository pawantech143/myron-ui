package com.pawan.boot.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.handler.UserRoleAuthorizationInterceptor;

import com.pawan.boot.domain.UserAccount;
import com.pawan.boot.service.UserAccountService;

@Controller
public class RegistrationController {

	@Autowired
	private UserAccountService service;
	
	@ModelAttribute
	public void loadFormData(Model model,UserAccount account) {
		
		model.addAttribute("account", account);
		model.addAttribute("countries", service.loadCountries());
	}
	@GetMapping("/register")
	public String loadRegForm() {

		
		return "registration";
	}

	@GetMapping("/uniqueMailCheck")
	public @ResponseBody String isEmailUnique(@RequestParam("email") String email) {

		return service.isUniqueEmail(email)? "UNIQUE" : "EMAIL IS EXISTED";
	}

	@GetMapping("/countryChange")
	public @ResponseBody Map<Integer, String> handleCountryChangeEvnt(@RequestParam("countryId") Integer countryId) {

	
		return service.loadStatesByCountryId(countryId);

	}

	@GetMapping("/statesChange")
	public @ResponseBody Map<Integer, String> handleStatesChangeEvnt(@RequestParam("stateId") Integer stateId) {

		System.out.println(service.loadCitiesByStateId(stateId));
	return service.loadCitiesByStateId(stateId);

	}
	@PostMapping("/userRegistration")
	public String handleRegisterBtn(@ModelAttribute UserAccount account,Model model) {
		
		
		boolean isSaved=service.saveUserAccount(account);
		 if(isSaved) {
			 
			model.addAttribute("successMsg", "your registration almost completed please check your mail to unlock it");
		 }
		 else {
			 model.addAttribute("failureMsg", "failed to register the user");
		 }
		
		return "registration";
	}
	
	
}
