package com.example.demo.controller;



import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;

@Controller
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	private StudentRepository studentrepository;


	
	@PostMapping("/showresult")
	public String showresult(@ModelAttribute Student student,Model model, RedirectAttributes redirectAttributes) {
		Student studentt=studentrepository.getByhallticketnumberAndbirthdateyear(student.getHallticketnumber(), student.getBirthdateyear());
		
		if(Objects.nonNull(studentt)){
			model.addAttribute("studentt",studentt);
			model.addAttribute("name", studentt.getName());
			redirectAttributes.addFlashAttribute("message","Welcome");
			return"studentresult";
			
		}else {
			redirectAttributes.addFlashAttribute("message","Wrong input please try again");
			return"redirect:/home/all";
		}	
	}
}
