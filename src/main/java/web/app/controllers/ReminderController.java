package web.app.controllers;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import web.app.configuration.StatusType;
import web.app.entities.Reminder;
import web.app.scheduler.Sheduler;
import web.app.service.ReminderService;
import web.app.service.UserService;

@Controller
@RequestMapping(value="/reminder/")
public class ReminderController {
	private static final Logger LOG = Logger.getLogger(ReminderController.class);
	
	@Autowired
	private ReminderService reminderService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/save.html", method = RequestMethod.POST )
	public ModelAndView reminderPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LOG.info("saving reminder in database...");
		
		if (UserController.user == null) {
			return new ModelAndView("/pages/login");
		}
		
		String message = request.getParameter("message");
		SimpleDateFormat smplDate = new SimpleDateFormat("MM/dd/yyyy HH:mm");
		Date date = smplDate.parse(request.getParameter("date"));
		Timestamp time = new Timestamp(date.getTime());
		String receiver = request.getParameter("receiver");
		String deliveryType = request.getParameter("type");
		
		Reminder instance = new Reminder();
		instance.setDate(time);
		instance.setDelivery(deliveryType);
		instance.setReceiver(receiver);
		instance.setMessage(message);
		instance.setDelivery(deliveryType);
		instance.setStatus(String.valueOf(StatusType.WAITING));
		
		reminderService.addReminder(instance);
		LOG.info("saved reminder in database...");
		
		Sheduler.set(instance, UserController.user);
		return reminderGet(request, response);
	}
	
	@RequestMapping(value = "/save.html", method = RequestMethod.GET )
	public ModelAndView reminderGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LOG.info("show all saved reminders ...");
		return (UserController.user == null) ? new ModelAndView("/pages/login"):new ModelAndView("/pages/messages");
	}
}
