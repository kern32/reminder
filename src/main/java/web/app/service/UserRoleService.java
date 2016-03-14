package web.app.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.app.dao.UserRoleDAO;
import web.app.entities.UserRole;

@Service
public class UserRoleService {

	private static UserRoleService instance = null;
	private static Logger log = Logger.getLogger("file");
	
	@Autowired
	private UserRoleDAO userRoleDAO;
	
	public UserRoleService() {
		log.info("UserRoleService: default constr");
	}

	public static UserRoleService getInstance() {
		log.info("UserRoleService: singleton get instance");
		if (instance == null) {
			synchronized (UserRoleService.class) {
				if (instance == null) {
					return new UserRoleService();
				}
			}
		}
		return instance;
	}
	
	public void setRole(UserRole entity){
		log.info("UserRoleService: set role for user");
		userRoleDAO.setUserRole(entity);
	}
}
