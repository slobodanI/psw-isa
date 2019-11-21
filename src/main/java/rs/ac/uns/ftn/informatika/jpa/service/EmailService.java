package rs.ac.uns.ftn.informatika.jpa.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.jpa.dto.EmailDTO;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private Environment env;

	@Async
	public void sendNotificaitionAsync(EmailDTO email) throws MailException, InterruptedException, MessagingException {

		System.out.println("Slanje emaila...");
		/*
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo("psa.isa.usr@gmail.com");
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject(email.getNaslov());
		mail.setText(email.getTelo());
		System.out.println("Pre slanja...");
		javaMailSender.send(mail);
		*/
		
		MimeMessage message=javaMailSender.createMimeMessage();
	    MimeMessageHelper helper;
	    helper=new MimeMessageHelper(message,true);
	    helper.setTo("psa.isa.usr@gmail.com");
	    helper.setFrom(env.getProperty("spring.mail.username"));
	    helper.setSubject("nscck");
	    helper.setText("dddd");
	    javaMailSender.send(message);

		System.out.println("Email poslat!");
	}

	public void sendNotificaitionSync(EmailDTO email) throws MailException, InterruptedException {

		System.out.println("Slanje emaila...");

		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo("psa.isa.usr@gmail.com");
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject(email.getNaslov());
		mail.setText(email.getTelo());
		System.out.println("Pre slanja...");
		javaMailSender.send(mail);

		System.out.println("Email poslat!");
	}

}