package web.app.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import web.app.dao.UserDAO;
import web.app.entities.Users;


@Service
public class UserService{

	private static UserService instance = null;
	private static Logger log = Logger.getLogger("file");
	
	@Autowired
	private UserDAO userDAO;

	public UserService() {
		log.info("UserService: default constr");
	}

	public static UserService getInstance() {
		log.info("UserService: get singleton instance");
		if (instance == null) {
			synchronized (UserService.class) {
				if (instance == null) {
					return new UserService();
				}
			}
		}
		return instance;
	}

	public void addUser(Users user) {
		log.info("UserService: add user");
		userDAO.addUser(user);
	}
	
	public void updateUser(Users user) {
		log.info("UserService: update user");
		userDAO.updateUser(user);
	}
	
	public Users findByEmailAndPhone(String email, String password){
		log.info("UserService: find user by email and phone");
		return userDAO.findByEmailAndPhone(email, password);
	}
	
	public Users findByEmailAndPassword(String email, String password) {
		log.info("UserService: find user by email and password");
		return userDAO.findByEmailAndPassword(email, password);
	}
	
	public Users findByEmail(String email) {
		log.info("UserService: find user by email");
		return userDAO.findByEmail(email);
	}
	
	public Users findByName(String name) {
		log.info("UserService: find user by name");
		return userDAO.findByUsername(name);
	}
	
	public Users findByUserId(int id) {
		log.info("UserService: find user by id");
		return userDAO.findByUserId(id);
	}
	
	public Users getActiveUser() {
		log.info("UserService: get currently active user");
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		return findByName(username);
	}
}
