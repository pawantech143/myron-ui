package com.pawan.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pawan.boot.domain.UnlockAccount;
import com.pawan.boot.domain.UserAccount;
import com.pawan.boot.service.UserAccountService;

@Controller
public class UnlockAccountController {

	@Autowired
	private UserAccountService service;

	@GetMapping("/loadUnlockAccForm")
	public String loadUnlockAccountForm(@RequestParam("email") String email, Model model) {

		UnlockAccount account = new UnlockAccount();
		account.setEmail(email);
		model.addAttribute("userAcc", account);
		return "unlockAcc";
	}

	@PostMapping("/unlockAcc")
	public String handleSubmitBtn(@ModelAttribute("userAcc") UnlockAccount unlockAccount, Model model) {

		boolean isValid = service.isTempPwdVaild(unlockAccount.getEmail(), unlockAccount.getTempPwd());
		if (isValid) {

			service.unlockAccount(unlockAccount.getEmail(), unlockAccount.getNewPwd());
			model.addAttribute("successMsg", "account unlocked successfully.<a href=\"index\">login Here</a>");

		} else {

			model.addAttribute("failureMsg", "temporary password is incorrect");
		}
		return "unlockAcc";

	}
}
