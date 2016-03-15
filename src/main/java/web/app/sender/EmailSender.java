package web.app.sender;

import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import web.app.entities.Reminder;
import web.app.entities.Users;

@Configuration
@PropertySource("classpath:../application-config.properties")
public class EmailSender extends Sender {

	private static Logger log = Logger.getLogger("file");
	private static Properties props;
	private static Session session;
	
	@Autowired
	private Environment env;
	
	@PostConstruct
	public void init() {
		props = getProperties();
		session = getSession();
	}
	
	@Bean
	public Session getSession() {
		log.info("EmailSender: get mail session");
		return Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(env.getProperty("mail.address"), env.getProperty("mail.password"));
			}
		});
	}

	@Bean
	public Properties getProperties() {
		log.info("EmailSender: get mail properties");
		props = new Properties();
		props.put("mail.smtp.auth", env.getProperty("mail.smtp.auth"));
		props.put("mail.smtp.starttls.enable", env.getProperty("mail.smtp.starttls.enable"));
		props.put("mail.smtp.host", env.getProperty("mail.smtp.host"));
		props.put("mail.smtp.port", env.getProperty("mail.smtp.port"));
		props.put("mail.subject.registration", env.getProperty("mail.subject.registration"));
		props.put("mail.address", env.getProperty("mail.address"));
		props.put("mail.contact", env.getProperty("mail.contact"));
		
		return props;
	}

	public boolean registration(Users entity) {
		log.info("EmailSender: send registration message on email");
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(props.getProperty("mail.address")));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(entity.getEmail()));
			message.setSubject(props.getProperty("mail.subject.registration"));
			message.setText("Dear, " + entity.getUsername() + "\n\nYou were successfully registered");
			Transport.send(message);
		} catch (AddressException e) {
			log.error("EmailSender: catch AddressException while sending registration message on email, full stack trace follows:", e);
			e.printStackTrace();
			return false;
		} catch (MessagingException e) {
			log.error("EmailSender: catch MessagingException while sending registration message on email, full stack trace follows:", e);
			e.printStackTrace();
			return false;
		}
		log.info("EmailSender: registration message was sent on email successfully");
		return true;
	}

	public boolean send(Reminder entity) {
		log.info("EmailSender: send remind message");
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(props.getProperty("mail.address")));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(entity.getReceiver()));
			message.setSubject(entity.getSubject());
			message.setText(entity.getMessage());
			Transport.send(message);
		} catch (AddressException e) {
			log.error("EmailSender: catch AddressException while sending reminder message on email, full stack trace follows:", e);
			e.printStackTrace();
			return false;
		} catch (MessagingException e) {
			log.error("EmailSender: catch MessagingException while sending reminder message on email, full stack trace follows:", e);
			e.printStackTrace();
			return false;
		}
		log.info("EmailSender: reminder was sent on email successfully");
		return true;
	}
	
	public boolean sendComment(Users user, String subject, String comment) {
		log.info("EmailSender: send comment message");
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(props.getProperty("mail.address")));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(props.getProperty("mail.contact")));
			message.setSubject(subject);
			message.setText(user.getUsername() + ":\n\n" + comment);
			Transport.send(message);
		} catch (AddressException e) {
			log.error("EmailSender: catch AddressException while sending comment on email, full stack trace follows:", e);
			e.printStackTrace();
			return false;
		} catch (MessagingException e) {
			log.error("EmailSender: catch MessagingException while sending comment on email, full stack trace follows:", e);
			e.printStackTrace();
			return false;
		}
		log.info("EmailSender: comment message was sent on email successfully");
		return true;
	}
}
