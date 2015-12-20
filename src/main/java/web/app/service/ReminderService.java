package web.app.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.app.dao.ReminderDAO;
import web.app.dao.UserDAO;
import web.app.entities.Reminder;

@Service(value = "ReminderService")
public class ReminderService {

	private static ReminderService instance = null;
	private static final Logger LOG = Logger.getLogger(ReminderService.class);
	
	@Autowired
	private ReminderDAO reminderDao;
	
	@Autowired
	private UserDAO userDao;

	public ReminderService() {
		LOG.info("default constr ReminderService");
	}

	public static ReminderService getInstance() {
		LOG.info("singleton ReminderService getInstance");
		if (instance == null) {
			synchronized (ReminderService.class) {
				if (instance == null) {
					return new ReminderService();
				}
			}
		}
		return instance;
	}
	
	public void addReminder(Reminder r){
		LOG.info("UserService: adding reminder");
		reminderDao.addReminder(r);
	}
	
	public Reminder findReminder(String id){
		LOG.info("UserService: finding reminder");
		return reminderDao.findReminderById(id);
	}
	
	public void updateReminder(Reminder r){
		LOG.info("UserService: updating reminder");
		reminderDao.updateReminder(r);
	}
	
	public void deleteReminder(Reminder r){
		LOG.info("UserService: deleting reminder");
		reminderDao.deleteReminder(r);
	}
}
