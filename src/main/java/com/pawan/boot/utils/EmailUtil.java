package com.pawan.boot.utils;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtil {

	@Autowired
	private JavaMailSender mailSender;

	public boolean seendMail(String to, String subject, String body) {

		boolean isSend = true;
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
			messageHelper.setTo(to);
			messageHelper.setSubject(subject);
			messageHelper.setText(body,true);
			mailSender.send(mimeMessage);
			isSend=true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isSend;

	}
}
