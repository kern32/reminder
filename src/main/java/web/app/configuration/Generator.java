package web.app.configuration;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

import org.apache.log4j.Logger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import web.app.controllers.UserController;

public class Generator {

	private static final Logger LOG = Logger.getLogger(UserController.class);

	public static String encryptPass(String password) throws GeneralSecurityException, UnsupportedEncodingException {
		LOG.info("encrypting password........");
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		return hashedPassword;
	}
}
