package com.sarkar.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {
	@Autowired
	private JavaMailSender mailSender;

	public boolean sendEmail(String subject, String body, String to) {
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true); // Enable multi-part support

			helper.setSubject(subject);
			helper.setText(body, true); // Set true to allow HTML content
			helper.setTo(to);
			

			mailSender.send(mimeMessage);
		} catch (Exception e) {
			e.printStackTrace();
			return false; // Return false if email sending fails
		}
		return true;
	}
}

