package com.example.IMS.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.IMS.entities.User;
import com.example.IMS.services.UserService;
import com.example.IMS.services.UserServiceImplementation;
@Controller
public class MyControllers {
	@Autowired
	private UserService userService;
	@GetMapping("/regPage")
	public String openRegPage(Model model) {
		model.addAttribute("user",new User());
		return "register";
	}
	@PostMapping("/regForm")
	public String submitRegForm(@ModelAttribute("user") User user , Model model)
	{
		boolean status = userService.registerUser(user);
		if(status) {
			model.addAttribute("successMsg","user registered successfully");
			return "redirect:/loginPage";
		}else {
			model.addAttribute("errorMsg","user not registered due to some error");
		}
		return "register";
	}
	
	@GetMapping("/loginPage")
	public String openLoginPage(Model model)
	{
		model.addAttribute("user",new User());
		return "login";
	}
	@PostMapping("/loginForm")
	public String submitLoginForm(@ModelAttribute("user") User user, Model model)
	{
		
	User validUser = userService.loginUser(user.getEmail(), user.getPassword());
		if(validUser != null) {
			return "redirect:/InventoryPage";
		}else {
			
			model.addAttribute("errorMsg","Email id and password didn't matched!!");
			return "login";
		}

	}
	 @GetMapping("/InventoryPage")
	   public String openInventoryPage(Model model) {
	       return "inventory";
	   }
}
