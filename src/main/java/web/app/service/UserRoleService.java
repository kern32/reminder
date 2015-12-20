package web.app.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.app.dao.ReminderDAO;
import web.app.dao.UserDAO;
import web.app.dao.UserRoleDAO;
import web.app.entities.Reminder;
import web.app.entities.UserRole;

@Service
public class UserRoleService {

	private static UserRoleService instance = null;
	private static final Logger LOG = Logger.getLogger(UserRoleService.class);
	
	@Autowired
	private UserRoleDAO userRoleDao;
	
	public UserRoleService() {
		LOG.info("default constr UserRoleService");
	}

	public static UserRoleService getInstance() {
		LOG.info("singleton UserRoleService getInstance");
		if (instance == null) {
			synchronized (UserRoleService.class) {
				if (instance == null) {
					return new UserRoleService();
				}
			}
		}
		return instance;
	}
	
	public void setRole(UserRole u){
		LOG.info("UserRoleService: setting role for user");
		userRoleDao.setUserRole(u);
	}
}
