package web.app.dao;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import web.app.entities.UserRole;

@Repository
public class UserRoleDAO {

	private static Logger log = Logger.getLogger("file");

	@PersistenceContext
	EntityManager entityManager;

	public UserRoleDAO() {
		log.info("UserRoleDAO: default constr UserRolesDAO");
	}

	@Transactional
	public void setUserRole(UserRole entity) {
		entityManager.persist(entity);
		log.info("UserRolesDAO: entity manager binds roleId and userId");
	}
}
