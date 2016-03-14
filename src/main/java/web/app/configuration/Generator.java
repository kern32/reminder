package web.app.configuration;

import org.apache.log4j.Logger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Generator {

	private static Logger log = Logger.getLogger("file");

	public static String encryptPass(String password) {
		log.info("Generator: encrypting password");
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.encode(password);
	}
}
