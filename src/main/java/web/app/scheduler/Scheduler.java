package web.app.scheduler;

import java.text.ParseException;
import java.util.Date;

import org.apache.log4j.Logger;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.quartz.impl.StdSchedulerFactory;

import web.app.entities.Reminder;
import web.app.entities.Users;
import web.app.service.ReminderService;

public class Scheduler {
	private static Logger log = Logger.getLogger("file");

	public static void set(Reminder reminder, Users user, ReminderService reminderService) throws ParseException {
		log.info("JobExecuter: set reminder schedule");
		try {
			JobDetail messageDetail = new JobDetail();
			messageDetail.setName(String.valueOf(reminder.getId()));
			JobDataMap jobDataMap = new JobDataMap();
			jobDataMap.put("reminder", reminder);
			jobDataMap.put("reminderService", reminderService);
			
			messageDetail.setJobDataMap(jobDataMap);
			messageDetail.setJobClass(JobExecuter.class);
			
			log.info("JobExecuter: configure scheduler time");
			SimpleTrigger trigger = new SimpleTrigger();
			trigger.setName("Reminder trigger");
			
			Date startDate = reminder.getDate();
			trigger.setStartTime(startDate);

			log.info("JobExecuter: make schedule for current reminder");
			org.quartz.Scheduler scheduler =  new StdSchedulerFactory().getScheduler();
			scheduler.start();
			scheduler.scheduleJob(messageDetail, trigger);

			log.info("JobExecuter: finished schedule for current reminder");
		} catch (SchedulerException e) {
			log.error("JobExecuter: error while setting schedule for current reminder, full stack trace follows:", e);
			e.printStackTrace();
		}
	}
}
