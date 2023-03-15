package net.javaguides.springboot;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class AppController {
	
	@Autowired
	private UserRepositary repo;
	@Autowired
	private EmailSenderService senderService;
	private String userIdEmail;
	private String userFirstName;
	private String userLastName;

	
	
	@GetMapping("")
	public String viewHomePage() {
		return "index";
	}
	
	@GetMapping("/register")
	public String showSignUpForm(Model model){
		model.addAttribute("user", new User());
		return "signup_form";
	}

	@PostMapping("/process_register")
	public String processRegistration(User user) {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedPassword = encoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		repo.save(user);
		
		
		return "register_success";
		
	}
	
	@GetMapping("/list_users")
	public String viewUserList(Model model) {
		List<User> listUsers = repo.findAll();
		model.addAttribute("listUsers", listUsers);
		return "users";
	}
	
	@GetMapping("/send_mail/{id}")
	public String emailSend(@PathVariable Long id,Model model) {
		Optional<User> userEmail = repo.findById(id);
		userIdEmail = userEmail.get().getEmail();
		model.addAttribute("userIdEmail", userIdEmail);   
		return "send_email";
		
	}
	
	@RequestMapping(path = "/mail_sent", method = RequestMethod.POST)
	public String sendMail(@RequestParam("remail") String remail, @RequestParam("sub") String sub, @RequestParam("body") String body) {
		
		senderService.sendEmail(remail,sub,body);
		return "successful";
	}
	
	@GetMapping("/edit/{id}")
	public String editPage(@PathVariable Long id,Model model){
		Optional<User> userDetails = repo.findById(id);
		userIdEmail = userDetails.get().getEmail();
		userFirstName = userDetails.get().getFirstName();
		userLastName = userDetails.get().getLastName();
		
		model.addAttribute("userid", repo.findById(id).get().getId());
		model.addAttribute("userIdEmail", userIdEmail);
		model.addAttribute("userFirstName", userFirstName);
		model.addAttribute("userLastName", userLastName);
		
		return "edit";
	}
	
	@RequestMapping(path = "/edit_success/{id}", method = RequestMethod.POST)
	public String editUser(@PathVariable Long id,@RequestParam("remail") String email, @RequestParam("fn") String firstName, @RequestParam("ln") String lastName) {
		User existingUser = repo.findById(id).get();
		existingUser.setId(id);
		existingUser.setEmail(email);
		existingUser.setFirstName(firstName);
		existingUser.setLastName(lastName);
		repo.save(existingUser);

		return "redirect:/list_users";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteUser(@PathVariable Long id) {
		repo.deleteById(id);
		return "redirect:/list_users";
	}
}
