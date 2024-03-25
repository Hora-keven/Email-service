package com.example.microserviceemail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.microserviceemail.dtos.EmailRecordDto;
import com.example.microserviceemail.dtos.EmailResponseDto;
import com.example.microserviceemail.service.EmailService;

@RestController
@RequestMapping("email")
public class EmailController {

	@Autowired
	private EmailService service;
	
	@PostMapping
	public ResponseEntity<EmailResponseDto> sendEmail(@ModelAttribute EmailRecordDto data){
		return ResponseEntity.status(HttpStatus.CREATED).body(service.sendMail(data));
	}
}
