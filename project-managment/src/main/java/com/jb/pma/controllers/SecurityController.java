package com.jb.pma.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.jb.pma.dao.UserAccountRepository;
import com.jb.pma.entities.Employee;
import com.jb.pma.entities.UserAccount;

@Controller
public class SecurityController {
	
	@Autowired
	UserAccountRepository accountRepo;
	
	@Autowired
	BCryptPasswordEncoder bCryptEncoder;

	@GetMapping("/register")
	public String register(Model model) {
		UserAccount userAccount = new UserAccount();
		model.addAttribute("userAccount", userAccount);
		
		return "security/register";
	}
	
	@PostMapping("/register/save")
	public String saveUser(UserAccount user, Model Model) {
		user.setPassword(bCryptEncoder.encode(user.getPassword()));
		accountRepo.save(user);
		
		return "redirect:/";
	}
}
