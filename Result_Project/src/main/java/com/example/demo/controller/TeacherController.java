package com.example.demo.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.Student;
import com.example.demo.model.Teacher;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.TeacherRepository;


@Controller
@RequestMapping("/teacher")
public class TeacherController {
	
//<------------------------------This is Home Mapping------------------------------------------>	
	@Autowired
	private StudentRepository studentrepository;
	

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private TeacherRepository teacherrepository;

	@GetMapping("/edit")
	public String edit(Teacher teacher,Model model) {
		return "redirect:/teacher/allstudents";
	}
	
//<---------------------Here we get List of Students in to techers homepage--------------------->
	@GetMapping("/allstudents")
	public String getAllStudent(Model model,@ModelAttribute Teacher teacher) {
		List<Student> studentList = new ArrayList<>();
		studentrepository.findAll().forEach(studentList::add);
		model.addAttribute("studentList", studentList);
		model.addAttribute("email", teacher.getEmail());
		
		return "teachers";
	}

	
	
//<----------------Here we add the students in table and database by using form-------------->
	@GetMapping("/addstudents")
	public String getStudentForm(Model model) {
		Student student = new Student();
		model.addAttribute("student", student);
		model.addAttribute("pageTitle", "Create New Student");
		return "studentform";
	}
	
	@PostMapping("/savestudents")
	public String saveStudent(Student student, Model model, RedirectAttributes redirectAttributes) {
		Student std=studentrepository.findByhallticketnumber(student.getHallticketnumber());
		   if( Objects.nonNull(std)){
			   redirectAttributes.addFlashAttribute("sms","Hallticket number already present in database");
		    	return"redirect:/teacher/addstudents";
		    }else {
			studentrepository.save(student);
		 if(studentrepository.save(student) != null) {
		
			redirectAttributes.addFlashAttribute("message", "Student Details has been saved");
			return "redirect:/teacher/allstudents";
			}else {
				redirectAttributes.addFlashAttribute("message", "Student Details has not been saved");
				return "redirect:/teacher/allstudents";
			}
		    }
		}
	
	

//<----------- Here we Delete Student from Table & DataBase By using this methods------------------>
		@GetMapping("/deletestudent/{id}")
		public String deleteStudentById(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
			studentrepository.deleteById(id);
			return "redirect:/teacher/allstudents";
		}
		
		
//<---- Here we Edit & Update a Student And Save it in to DataBase By using this methods---------->
		@GetMapping("{id}")
		public String editStudentById(@PathVariable("id") int id, Model model) {
			Student s = studentrepository.findById(id).get();
			model.addAttribute("student", s);
			model.addAttribute("pageTitle", "Edit Student with Id " + id);
			return "studentform";
		}
	
	
	
	
	
	
	
//<----------------Here we add the teachers in table and database by using form-------------->	
	@GetMapping("/addteacher")
	public String getTeacherForm(Model model) {
		Teacher teacher = new Teacher();
		model.addAttribute("teacher", teacher);
		model.addAttribute("pageTitle", "Create New Teacher");
		return"teacherform";
	}

	@PostMapping("/saveteacher")
	public String saveteacher(Teacher teacher, Model model, RedirectAttributes redirectAttributes) {

		
		Teacher std=teacherrepository.findByemail(teacher.getEmail());
   if( Objects.nonNull(std)){
			   redirectAttributes.addFlashAttribute("smss","Teacher Email already present in database");
		    	return"redirect:/teacher/addteacher";
     }else {
		String encodedPassword = passwordEncoder.encode(teacher.getPassword());
        teacher.setPassword(encodedPassword);
		
		teacherrepository.save(teacher);
		
		if(teacherrepository.save(teacher) != null) {
		redirectAttributes.addFlashAttribute("message", "Teacher Details has been saved");
		return "redirect:/teacher/allstudents";
		}else {
			redirectAttributes.addFlashAttribute("message", "Teacher Details has not been saved");
			return "redirect:/teacher/allstudents";
		}
     }
	}
	
	

	
//<---------------------Here we get List of Teacher in to techers homepage--------------------->
		@GetMapping("/allteachers")
		public String getAllteacher(Model model) {
			List<Teacher> teacherlist = new ArrayList<>();
			teacherrepository.findAll().forEach(teacherlist::add);
			model.addAttribute("teacherlist", teacherlist);
			return "teacherlist";
		}
		
		
		
//<----------- Here we Delete Teacher from  DataBase By using this methods------------------>
			@GetMapping("/deleteteacher/{teacherId}")
			public String deleteteacherById(@PathVariable("teacherId") int teacherId, RedirectAttributes redirectAttributes) {
				teacherrepository.deleteById(teacherId);
				return "redirect:/teacher/allteachers";
			}
	
	

	
	
}