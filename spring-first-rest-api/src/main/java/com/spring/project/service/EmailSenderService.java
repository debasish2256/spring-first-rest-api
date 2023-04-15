package com.spring.project.service;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSenderService {
	public static void main(String[] args) {
		final String username = "debasishmahana49@gmail.com";
		final String password = "xxxxxxxxxxxxxxxxxxxxxxx";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.debug", "true");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("debasishmahana49@gmail.com")); 
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("debasishmahana49@gmail.com")); 
			message.setSubject("Testing Subject: Geek");
			message.setText("Dear Recipient," + "\n\n This is a test email sent using JavaMail API.");

			Transport.send(message);

			System.out.println("Email sent successfully.");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
