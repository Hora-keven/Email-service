package com.example.microserviceemail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.microserviceemail.dtos.EmailRecordDto;
import com.example.microserviceemail.dtos.EmailResponseDto;

import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Value("${spring.mail.email}")
	private String sender;
	
	public EmailResponseDto sendMail(EmailRecordDto data) {
		try {
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper mimeMessageHelper;
			mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
			mimeMessageHelper.setFrom(sender);
			mimeMessageHelper.setTo(data.to());
			mimeMessageHelper.setText(data.body(), true);
			mimeMessageHelper.setSubject(data.subject());
			mimeMessageHelper.setCc(data.cc());
			
			var file = data.attchment();
			if(file != null) {
				for(int i = 0; i < data.attchment().length; i++) {
					mimeMessageHelper.addAttachment(file[i].getOriginalFilename(), new ByteArrayResource(file[i].getBytes()));
				}
			}
			
			
			javaMailSender.send(mimeMessage);
			
			return new EmailResponseDto("Email send successfully!");
		}
		
		catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	
		
	}
	

}
