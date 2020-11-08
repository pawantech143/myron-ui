package com.pawan.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pawan.boot.domain.UserAccount;

@Controller
public class UserAccountController {

	public String loadForm(Model model) {

		UserAccount cmd = new UserAccount();
		model.addAttribute("cmd", cmd);

		return "UserRegister";

	}

	@GetMapping("/validateEmail")
	public @ResponseBody String validateEmail(@RequestParam String email) {

		return null;
	}

	public String loadStateOnClickCountry(@RequestParam Integer countryId, Model model) {

		return null;
	}

	public String loadCityOnClickState(@RequestParam Integer stateId, Model model) {

		return null;
	}

	public String handleSbmtButton(@ModelAttribute UserAccount cmd, Model model) {

		return null;

	}
}
