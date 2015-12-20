package web.app.scheduler;

import java.text.ParseException;
import java.util.Date;

import org.apache.log4j.Logger;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.quartz.impl.StdSchedulerFactory;

import web.app.entities.Reminder;
import web.app.entities.Users;

public class Sheduler {
	private static final Logger LOG = Logger.getLogger(Scheduler.class);

	public static void set(Reminder reminder, Users user) throws ParseException {
		LOG.info("setting reminder...");

		try {
			JobDetail messageDetail = new JobDetail();
			messageDetail.setName(String.valueOf(reminder.getId()));
			JobDataMap jobDataMap = new JobDataMap();
			jobDataMap.put("reminder", reminder);
			jobDataMap.put("user", user);
			
			messageDetail.setJobDataMap(jobDataMap);
			messageDetail.setJobClass(JobExecuter.class);
			
			// configure the scheduler time
			SimpleTrigger trigger = new SimpleTrigger();
			trigger.setName("Reminder trigger");
			
			Date startDate = reminder.getDate();
			trigger.setStartTime(startDate);

			// schedule it
			Scheduler scheduler = new StdSchedulerFactory().getScheduler();
			scheduler.start();
			scheduler.scheduleJob(messageDetail, trigger);

			LOG.info("finished setting reminder...");
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

}
