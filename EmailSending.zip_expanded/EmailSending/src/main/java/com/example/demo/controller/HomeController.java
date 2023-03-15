package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Email;
import com.example.demo.service.EmailService;

@Controller
@RequestMapping("/open")
public class HomeController {
	


	@Autowired
	private EmailService emailservice;
	
	
	@GetMapping("/index")
		public String index() {
			return "email_sending";
		}
	
	@PostMapping("sendEmail")
	public String sendEmail(@ModelAttribute Email email,HttpSession session){
		
		emailservice.sendEmail(email);
		
		session.setAttribute("msg", "Email Sent Succesfully");
		
		return "redirect:/open/index";
	}
	

}
