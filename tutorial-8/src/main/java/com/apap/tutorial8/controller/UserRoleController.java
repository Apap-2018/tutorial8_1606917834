package com.apap.tutorial8.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.apap.tutorial8.model.PasswordModel;
import com.apap.tutorial8.model.UserRoleModel;
import com.apap.tutorial8.service.UserRoleService;

@Controller
@RequestMapping("/user")
public class UserRoleController {
	@Autowired
	private UserRoleService userService;
	
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	private ModelAndView addUserSubmit(@ModelAttribute UserRoleModel user,RedirectAttributes red, Model model) {
		String message = "";
		if (userService.addUser(user) != null) {
			message = "username dan password berhasil ditambahkan";
		}
		else {
			message = "password anda harus mengandung angka, huruf, dan memiliki sedikitnya 8 karakter";
		}
		
		ModelAndView modelAndView = new ModelAndView("redirect:/");
		red.addFlashAttribute("msg",message);
		return modelAndView;
		
	}
	@RequestMapping(value="/updatepassword", method=RequestMethod.GET)
	private String updatePassword() {
		return "update-password";
	}
	
	@RequestMapping(value="/passwordSubmit", method=RequestMethod.POST)
	public ModelAndView updatePasswordSubmit(@ModelAttribute PasswordModel password,
			RedirectAttributes red, Model model) {
		
		UserRoleModel user = userService.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		String message = userService.changeUserPass(password, user);

		ModelAndView modelAndView = new ModelAndView("redirect:/user/updatepassword");
		red.addFlashAttribute("msg",message);
		return modelAndView;
		
	}
}
