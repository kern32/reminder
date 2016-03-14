package web.app.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import web.app.entities.Users;

@Repository
public class UserDAO {

	private static Logger log = Logger.getLogger("file");

	@PersistenceContext
	EntityManager entityManager;

	public UserDAO() {
		log.info("UserDAO: default constr UserDAO");
	}

	@Transactional
	public void addUser(Users entity) {
		entityManager.persist(entity);
		log.info("UserDAO: entity manager persisted user");
	}
	
	@Transactional
	public void updateUser(Users entity) {
		entityManager.merge(entity);
		log.info("UserDAO: entity manager updated user");
	}
	
	public Users findByEmailAndPhone(String email, String phone) {
		log.info("UserDAO: entity manager is searching user by email and phone");
		TypedQuery<Users> query = entityManager.createQuery("FROM Users u where u.email = :email or u.phone= :phone", Users.class);
		query.setParameter("email", email);
		query.setParameter("phone", phone);
		return getUser(query);
	}

	public Users findByEmailAndPassword(String email, String password) {
		log.info("UserDAO: entity manager is searching user by email and pass");
		TypedQuery<Users> query = entityManager.createQuery("FROM Users u where u.email = :email and u.password = :password", Users.class);
		query.setParameter("email", email);
		query.setParameter("password", password);
		return getUser(query);
	}

	public Users findByEmail(String email) {
		log.info("UserDAO: entity manager is searching user by email");
		TypedQuery<Users> query = entityManager.createQuery("FROM Users u where u.email = :email", Users.class);
		query.setParameter("email", email);
		return getUser(query);
	}

	public Users findByUsername(String username) {
		log.info("UserDAO: entity manager is searching user by name");
		TypedQuery<Users> query = entityManager.createQuery("FROM Users u where u.username = :username", Users.class);
		query.setParameter("username", username);
		return getUser(query);
	}
	
	public Users findByUserId(int id) {
		log.info("UserDAO: entity manager is searching user by id");
		TypedQuery<Users> query = entityManager.createQuery("FROM Users u where u.id = :id", Users.class);
		query.setParameter("id", id);
		return getUser(query);
	}

	private Users getUser(TypedQuery<Users> query) {
		log.info("UserDAO: getting first user from result list");
		List<Users> result = query.getResultList();
		return result.isEmpty() ? null : (Users) result.get(0);
	}
}
