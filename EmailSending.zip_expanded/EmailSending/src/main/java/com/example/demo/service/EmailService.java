package com.example.demo.service;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.demo.model.Email;

@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender javamailsender;
	
	public void sendEmail( Email email) {
		
		try {
			MimeMessage message=javamailsender.createMimeMessage();
			MimeMessageHelper helper=new MimeMessageHelper(message);
			helper.setFrom("someshghuge85@gmail.com");
			helper.setTo(email.getTo());
			helper.setSubject(email.getSubject());
			helper.setText(email.getMessage());
			
			javamailsender.send(message);
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
