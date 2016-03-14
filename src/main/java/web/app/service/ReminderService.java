package web.app.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.app.dao.ReminderDAO;
import web.app.dao.UserDAO;
import web.app.entities.Reminder;

@Service
public class ReminderService {

	private static ReminderService instance = null;
	private static Logger log = Logger.getLogger("file");
	
	@Autowired
	private ReminderDAO reminderDAO;
	
	@Autowired
	private UserDAO userDAO;

	public ReminderService() {
		log.info("UserRoleService: default constr");
	}

	public static ReminderService getInstance() {
		log.info("UserRoleService: singleton get instance");
		if (instance == null) {
			synchronized (ReminderService.class) {
				if (instance == null) {
					return new ReminderService();
				}
			}
		}
		return instance;
	}
	
	public void addReminder(Reminder entity){
		log.info("UserService: add reminder");
		reminderDAO.addReminder(entity);
	}
	
	public void updateReminder(Reminder entity){
		log.info("UserService: update reminder");
		reminderDAO.updateReminder(entity);
	}
	
	public void deleteReminder(Reminder entity){
		log.info("UserService: delete reminder");
		reminderDAO.deleteReminder(entity);
	}
	
	public Reminder findReminder(int id){
		log.info("UserService: finding reminder by id");
		return reminderDAO.findReminderById(id);
	}
	
	public List<Reminder> findReminderByUserId(int userId, int firstResult, int maxResults){
		log.info("UserService: finding reminder by user id");
		return reminderDAO.findReminderByUserId(userId, firstResult, maxResults);
	}
}
