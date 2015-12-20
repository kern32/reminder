package web.app.dao;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import web.app.dao.UserDAO;
import web.app.entities.Role;
import web.app.entities.UserRole;
import web.app.entities.Users;

@Repository
public class UserRoleDAO {

	private static final Logger LOG = Logger.getLogger(UserRoleDAO.class);

	@PersistenceContext
	EntityManager entityManager;

	public UserRoleDAO() {
		LOG.info("default constr UserRolesDAO");
	}

	@Transactional
	public void setUserRole(UserRole u) {
		entityManager.persist(u);
		LOG.info("UserRolesDAO: entity manager binds roleID and userID");
	}
}
