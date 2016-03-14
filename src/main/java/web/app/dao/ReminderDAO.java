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
	private static Logger log = Logger.getLogger("file");
	
	@PersistenceContext
	EntityManager entityManager;
		
	public ReminderDAO(){
		log.info("ReminderDAO: default constr ReminderDAO");
	}

	public Reminder findReminderById (int id) {
		log.info("ReminderDAO: entity manager is searching reminder by Id");
		return entityManager.find(Reminder.class, id);
	}
	
	public List<Reminder> findReminderByUserId (int userId, int firstResult, int maxResults) {
		log.info("ReminderDAO: entity manager is searching reminder by userId");
		TypedQuery<Reminder> query = entityManager.createQuery("FROM Reminder r where r.userId= :userId order by date desc", Reminder.class);
		query.setParameter("userId", userId);
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResults);
		List<Reminder> resultList = query.getResultList();
		return resultList;
	}

	@Transactional
	public void addReminder(Reminder entity) {
		entityManager.persist(entity);
		log.info("ReminderDAO: entity manager persisted reminder");
	}
	
	@Transactional
	public void updateReminder(Reminder entity) {
		entityManager.merge(entity);
		log.info("ReminderDAO: entity manager updated reminder");
	}

	@Transactional
	public void deleteReminder(Reminder entity) {
		entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
		log.info("ReminderDAO: entity manager deleted reminder");
	}
}
