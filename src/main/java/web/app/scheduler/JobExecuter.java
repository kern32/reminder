package web.app.scheduler;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import web.app.configuration.DeliveryType;
import web.app.configuration.StatusType;
import web.app.entities.Reminder;
import web.app.entities.Users;
import web.app.sender.EmailSender;
import web.app.service.ReminderService;
//import web.app.sender.Skype4JavaSender;
//import web.app.sender.XmppManager;

public class JobExecuter implements Job {
	private static final Logger LOG = Logger.getLogger(JobExecuter.class);
	private static EmailSender emailAgent = new EmailSender();
	//private static Skype4JavaSender skypeAgent = new Skype4JavaSender();

	@Autowired
	private static ReminderService reminderService;

	public void execute(JobExecutionContext context) throws JobExecutionException {
		LOG.info("calling remind executer...");

		JobDetail messageDetail = context.getJobDetail();
		JobDataMap jobDataMap = messageDetail.getJobDataMap();

		Reminder reminder = (Reminder) jobDataMap.get("reminder");
		Users user = (Users) jobDataMap.get("user");

		String name = user.getName();
		String message = reminder.getMessage();
		String receiver = reminder.getReceiver();
		String deliveryType = reminder.getDelivery();

		if (DeliveryType.EMAIL.equals(deliveryType)) {
			if (emailAgent.remind(name, receiver, message)) {
				LOG.info("EMAIL sent...");
			}
		/*} else if (DeliveryType.SKYPE.equals(deliveryType)) {
			if (skypeAgent.remind(name, receiver, message)) {
				LOG.info("SKYPE sent...");
			}
		} else if (DeliveryType.PHONE.equals(deliveryType)) {
			if (jabberAgent.remind(name, receiver, message)) {
				System.out.println("SEND SMS");
			}*/
		}
		reminder.setStatus(String.valueOf(StatusType.DONE));
		reminderService.updateReminder(reminder);
	}

}
