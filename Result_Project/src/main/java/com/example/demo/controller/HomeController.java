package com.example.demo.controller;

import java.util.Objects;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.Email;
import com.example.demo.model.Student;
import com.example.demo.model.Teacher;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.TeacherRepository;
import com.example.demo.service.EmailService;



@Controller
@RequestMapping("/home") 
public class HomeController {
	
	 @Autowired
	 private PasswordEncoder passwordEncoder;
	 
	@Autowired
	private EmailService emailservice;
	
	@Autowired
	public StudentRepository studentrepository;
	
	@Autowired
	public TeacherRepository teacherrepository;
	
	
	
	
	@GetMapping("/all")
	public String home() {
		return "home";
	}
	
	@GetMapping("/studentloginn")
	public String studentloginn(Student student) {
		return "studentlogin";
	}
	
	
	@GetMapping("/indexstudent")   
	public String indexstudent() {
		return "emailsending";
	}
	@PostMapping("/sendEmailfromstudent")
	public String sendEmailfromstudent(@ModelAttribute Email email,HttpSession session){	
		emailservice.sendEmail(email);
		session.setAttribute("msg", "Email Sent Succesfully");
		return "redirect:/student/getresult";
	}

	
	@GetMapping("/studentlogout")
	public String studentlogout() {
		return"home";
	}
	
	
	@GetMapping("/teacherloginn")
	public String teacherloginn() {		
		return"teacherlogin";
	}
	
	
	
	 
	 	@PostMapping("/teacherlogin")
		public String teacherlogin(@ModelAttribute Teacher teacher, RedirectAttributes redirectAttributes,Model model) {

     	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();	    
        String Repopassword=teacherrepository.getPasswordByEmail(teacher.getEmail());
      
      
        	if(passwordEncoder.matches(teacher.getPassword(),Repopassword)) {
        		redirectAttributes.addFlashAttribute("message", "Successfully Login please edit  result");
				return "redirect:/teacher/edit";
			}else {
				redirectAttributes.addFlashAttribute("message", "Sorry Wrong info try again");
				return "redirect:/home/all";
			}		
		} 
	
	@GetMapping("/teacherlogout")
	public String teacherlogout() {
		return "home";
	}
	
	
	@GetMapping("/index")   
		public String index() {
		System.out.println("this is from index link");
			return "email_sending";
		}
	
	@PostMapping("/sendEmail")
	public String sendEmail(@ModelAttribute Email email,HttpSession session){
		emailservice.sendEmail(email);
		session.setAttribute("msg", "Email Sent Succesfully");
     	return "redirect:/teacher/edit";
	}
}