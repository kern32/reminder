package web.app.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import web.app.entities.Reminder;
import web.app.entities.Users;

@Repository
public class UserDAO {

	private static final Logger LOG = Logger.getLogger(UserDAO.class);

	@PersistenceContext
	EntityManager entityManager;

	public UserDAO() {
		LOG.info("default constr UserDAO");
	}

	@Transactional
	public void addUser(Users r) {
		entityManager.persist(r);
		LOG.info("UserDAO: entity manager persisted user");
	}
	
	@Transactional
	public void updateUser(Users r) {
		entityManager.merge(r);
		LOG.info("UserDAO: entity manager updated user");
	}
	

	public Users findByEmailAndPhone(String email, String phone) {
		LOG.info("UserDAO: entity manager is searching user by email and phone");
		TypedQuery<Users> query = entityManager.createQuery("FROM Users u where u.email = :email or u.phone= :phone", Users.class);
		query.setParameter("email", email);
		query.setParameter("phone", phone);
		return getUser(query);
	}

	public Users findByEmailAndPassword(String email, String psw) {
		LOG.info("UserDAO: entity manager is searching user by email and pass");
		TypedQuery<Users> query = entityManager.createQuery("FROM Users u where u.email = :email and u.psw = :psw", Users.class);
		query.setParameter("email", email);
		query.setParameter("psw", psw);
		return getUser(query);
	}

	public Users findByEmail(String email) {
		LOG.info("UserDAO: entity manager is searching user by email");
		TypedQuery<Users> query = entityManager.createQuery("FROM Users u where u.email = :email", Users.class);
		query.setParameter("email", email);
		return getUser(query);
	}

	public Users findByName(String name) {
		LOG.info("UserDAO: entity manager is searching user by name");
		TypedQuery<Users> query = entityManager.createQuery("FROM Users u where u.name = :name", Users.class);
		query.setParameter("name", name);
		return getUser(query);
	}
	
	public Users findByUserId(int id) {
		LOG.info("UserDAO: entity manager is searching user by id");
		TypedQuery<Users> query = entityManager.createQuery("FROM Users u where u.id = :id", Users.class);
		query.setParameter("id", id);
		return getUser(query);
	}

	private Users getUser(TypedQuery<Users> query) {
		List<Users> result = query.getResultList();
		return result.isEmpty() ? null : (Users) result.get(0);
	}

	public int countReminders(String name) {
		LOG.info("UserDAO: entity manager is counting number of reminders");

		Users user = findByName(name);
		Integer id = user.getId();

		TypedQuery<Reminder> query = entityManager.createQuery("FROM Reminder r where r.userId= :userId", Reminder.class);
		query.setParameter("userId", id);
		int count = query.getResultList().size();
		return count;
	}
}
