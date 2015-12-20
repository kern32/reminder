package web.app.sender;

import java.util.Properties;

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

import web.app.configuration.Generator;
import web.app.controllers.UserController;
import web.app.entities.Users;
import web.app.service.UserService;

public class EmailSender extends Sender {

	private static final Logger LOG = Logger.getLogger(UserController.class);
	private static Properties props;
	private static Session session;

	@Autowired
	private static UserService userService;

	private static Session getSession() {
		LOG.info("getting mail session");
		return Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("pswcode", "1111111@");
			}
		});
	}

	private static Properties setProperties() {
		LOG.info("setting mail properties");
		props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		return props;
	}

	public static boolean registration(String name, String email, String psw) {
		LOG.info("sending email registration");
		if (session == null) {
			props = setProperties();
			session = getSession();
		}
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("pswcode@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
			message.setSubject("Passcode");
			message.setText("Dear, " + name + "\n\nYour password is " + psw);
			Transport.send(message);
		} catch (AddressException e) {
			e.printStackTrace();
			return false;
		} catch (MessagingException e) {
			e.printStackTrace();
			return false;
		}
		LOG.info("registration email is sent");
		return true;
	}

	public static boolean forgotPassword(String email) {
		Users user = userService.findByEmail(email);

		if (user == null) {
			LOG.info("user not found");
			return false;
		}

		if (session == null) {
			LOG.info("mail session is null");
			props = setProperties();
			session = getSession();
		}

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("pswcode@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
			message.setSubject("Passcode");
			message.setText("Dear, " + user.getName() + "\n\nYou successfully registered ");
			Transport.send(message);
		} catch (AddressException e) {
			e.printStackTrace();
			return false;
		} catch (MessagingException e) {
			e.printStackTrace();
			return false;
		}
		LOG.info("message with forgot password is sent");
		return true;
	}

	public boolean remind(String name, String email, String reminder) {
		if (session == null) {
			LOG.info("session is null");
			props = setProperties();
			session = getSession();
		}
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("pswcode@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
			message.setSubject("Passcode");
			message.setText("Dear, " + name + "You got bellow reminder \n\n\n" + reminder);
			Transport.send(message);
		} catch (AddressException e) {
			e.printStackTrace();
			return false;
		} catch (MessagingException e) {
			e.printStackTrace();
			return false;
		}
		LOG.info("reminder is sent on email");
		return true;
	}
}
