package web.app.scheduler;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import web.app.configuration.DeliveryType;
import web.app.configuration.StatusType;
import web.app.entities.Reminder;
import web.app.sender.EmailSender;
import web.app.sender.PhoneSender;
import web.app.sender.SkypeSender;
import web.app.service.ReminderService;

public class JobExecuter implements Job {
	private static Logger log = Logger.getLogger("file");

	public void execute(JobExecutionContext context) throws JobExecutionException {
		log.info("JobExecuter: calling remind job executer");

		JobDetail messageDetail = context.getJobDetail();
		JobDataMap jobDataMap = messageDetail.getJobDataMap();

		Reminder reminder = (Reminder) jobDataMap.get("reminder");
		ReminderService reminderService = (ReminderService) jobDataMap.get("reminderService");
		DeliveryType deliveryType = reminder.getDelivery();

		boolean isSent = false;
		switch(deliveryType){
			case Email:
				EmailSender emailAgent = new EmailSender();
				isSent = emailAgent.send(reminder);
				break;
			case Skype:
				SkypeSender skypeAgent = new SkypeSender();
				isSent = skypeAgent.send(reminder);
				break;
			case Phone:
				PhoneSender phoneAgent = new PhoneSender();
				isSent = phoneAgent.send(reminder);
				break;
		}
		
		if (isSent){
			reminder.setStatus(StatusType.DONE);
			log.info("JobExecuter: change reminder status as done");
		} else {
			reminder.setStatus(StatusType.CANCELED);
			log.info("JobExecuter: change reminder status as canceled");
		}
		reminderService.updateReminder(reminder);
	}
}
