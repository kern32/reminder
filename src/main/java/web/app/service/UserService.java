package web.app.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.app.dao.UserDAO;
import web.app.entities.Users;


@Service
public class UserService{

	private static UserService instance = null;
	private static final Logger LOG = Logger.getLogger(UserService.class);
	
	@Autowired
	private UserDAO userDao;

	public UserService() {
		LOG.info("default constr UserService");
	}

	public static UserService getInstance() {
		LOG.info("singleton UserService getInstance");
		if (instance == null) {
			synchronized (UserService.class) {
				if (instance == null) {
					return new UserService();
				}
			}
		}
		return instance;
	}

	public Users findByEmailAndPhone(String email, String password){
		LOG.info("UserService: findByEmailAndPhone");
		return userDao.findByEmailAndPhone(email, password);
	}
	
	public void saveUser(Users user) {
		LOG.info("UserService: addUser");
		userDao.addUser(user);
	}
	
	public void updateUser(Users user) {
		LOG.info("UserService: updateUser");
		userDao.updateUser(user);
	}

	public Users findByEmailAndPassword(String email, String psw) {
		LOG.info("UserService: findByEmailAndPassword");
		return userDao.findByEmailAndPassword(email, psw);
	}
	
	public Users findByEmail(String email) {
		LOG.info("UserService: findByEmail");
		return userDao.findByEmail(email);
	}
	
	public Users findByName(String name) {
		LOG.info("UserService: findByName");
		return userDao.findByName(name);
	}
	
	public int countReminders(String email) {
		LOG.info("UserService: findReminders");
		return userDao.countReminders(email);
	}
	
	public Users findByUserId(int id) {
		LOG.info("UserService: findByUserID");
		return userDao.findByUserId(id);
	}
}
