package web.app.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import web.app.entities.Reminder;

@Repository
public class ReminderDAO {
	private static final Logger LOG = Logger.getLogger(ReminderDAO.class);
	
	@PersistenceContext
	EntityManager entityManager;
		
	public ReminderDAO(){
		LOG.info("default constr ReminderDAO");
	}

	@Transactional
	public void addReminder(Reminder r) {
		entityManager.persist(r);
		LOG.info("ReminderDAO: entity manager persisted reminder");
	}

	public Reminder findReminderById (String id) {
		LOG.info("ReminderDAO: entity manager is searching reminder by ID");
		TypedQuery<Reminder> query = entityManager.createQuery("FROM Reminder r where r.id= :id", Reminder.class);
		query.setParameter("id", id);
		return getReminder(query);
	}

	public void updateReminder(Reminder r) {
		entityManager.refresh(r);
		LOG.info("ReminderDAO: entity manager updated reminder");
	}

	public void deleteReminder(Reminder r) {
		entityManager.remove(r);
		LOG.info("ReminderDAO: entity manager deleted reminder");
		
	}
	
	private Reminder getReminder(TypedQuery<Reminder> query) {
		List<Reminder> result = query.getResultList();
		return result.isEmpty() ? null : (Reminder) result.get(0);
	}
}
