package web.app.controllers;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import web.app.configuration.DeliveryType;
import web.app.configuration.StatusType;
import web.app.entities.Reminder;
import web.app.scheduler.Scheduler;
import web.app.sender.SkypeSender;
import web.app.service.ReminderService;
import web.app.service.UserService;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Controller
public class ReminderController {
	private static Logger log = Logger.getLogger("file");
	
	@Autowired
	private ReminderService reminderService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/messages", method = RequestMethod.GET)
	public ModelAndView showReminderPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("ReminderController: showing message page");
		return new ModelAndView("pages/messages").addObject("user", userService.getActiveUser());
	}
	
	@RequestMapping(value = "/messages", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody public void getMessages(@RequestBody String data, HttpServletResponse response) throws IOException {
		log.info("ReminderController: showing all user messages");
		JsonParser parser = new JsonParser();
        JsonObject json = parser.parse(data).getAsJsonObject();
        int firstMessages = json.get("firstResult").getAsInt();
        int maxMessageResult = json.get("maxResult").getAsInt();

        HashMap<String, Object> JSONROOT = new HashMap<String, Object>();
		List<Reminder> reminderList = reminderService.findReminderByUserId(userService.getActiveUser().getId(), firstMessages, maxMessageResult + 1);
		
		boolean isNextPageExists = false;
		if (reminderList.size() > 5){
			isNextPageExists = true;
			reminderList.remove(maxMessageResult);
		}		
		
		JSONROOT.put("isNextPageExists", isNextPageExists);
		JSONROOT.put("maxResult", reminderList.size());
		JSONROOT.put("firstResult", firstMessages + reminderList.size());
		JSONROOT.put("result", reminderList);
		JSONROOT.put("Result", "OK");
   
		com.google.gson.Gson gson = new GsonBuilder()
	    .setDateFormat("MMM dd, yyyy HH:mm:ss")
		.setPrettyPrinting()
		.create();
		
		String jsonArray = gson.toJson(JSONROOT);
		response.setContentType("json");
		response.getWriter().print(jsonArray);
		log.info(jsonArray);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody void deleteMessages(@RequestBody String data, HttpServletResponse response) throws Exception  {
        log.info("ReminderController: received request to delete reminder");
		int id = new JsonParser().parse(data).getAsJsonObject().get("id").getAsInt();
		Reminder reminder = reminderService.findReminder(id);
		reminderService.deleteReminder(reminder);
		
		HashMap<String, Object> JSONROOT = new HashMap<String, Object>();
		JSONROOT.put("Result", "OK");

		com.google.gson.Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String jsonArray = gson.toJson(JSONROOT);
		response.setContentType("json");
		response.getWriter().print(jsonArray);
		log.info("ReminderController: deleted reminder with id: " + id);
	}
	
	@RequestMapping( value = {"/update", "/create"}, method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public void updateMessages(@RequestBody String data, HttpServletRequest req, HttpServletResponse res) throws Exception  {
		log.info("ReminderController: received request to update/create reminder");
		Reminder reminder = null;
		JsonParser parser = new JsonParser();
        JsonObject reminderJson = parser.parse(data).getAsJsonObject();
        
        String requestedDate = reminderJson.get("date").getAsString();
        Timestamp date = getDate(requestedDate);
        String subject= reminderJson.get("subject").getAsString();
        String message = reminderJson.get("reminder").getAsString();
        String receiver = reminderJson.get("receiver").getAsString();
        String requestedDelivery = reminderJson.get("delivery").getAsString();
        DeliveryType delivery = DeliveryType.valueOf(requestedDelivery);
        StatusType status = getStatusType(date);
		
        if (!reminderJson.get("id").isJsonNull()) {
        	int id = reminderJson.get("id").getAsInt();
        	reminder = reminderService.findReminder(id);
        	reminder.setDate(date);
            reminder.setDelivery(delivery);
            reminder.setSubject(subject);
            reminder.setMessage(message);
            reminder.setReceiver(receiver);
            reminder.setStatus(status);
            reminderService.updateReminder(reminder);
            log.info("ReminderController: reminder was updated");
        } else {
        	reminder = new Reminder.Builder()
        	.userId(userService.getActiveUser().getId())
        	.date(date)
        	.delivery(delivery)
        	.subject(subject)
        	.message(message)
        	.receiver(receiver)
        	.status(status)
        	.build();
            reminderService.addReminder(reminder);
            log.info("ReminderController: reminder was created");
        }
        
		if (reminder.getDelivery() == DeliveryType.Skype){
			if (!SkypeSender.isContactAdded(receiver)) {
				SkypeSender.addContact(receiver);
			}
		}
        
        if (getStatusType(date) != StatusType.CANCELED){
        	Scheduler.set(reminder, userService.getActiveUser(), reminderService);
        }
        
		HashMap<String, Object> JSONROOT = new HashMap<String, Object>();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		res.setContentType("json");
		JSONROOT.put("Result", "OK");
		String jsonArray = gson.toJson(JSONROOT);
		log.info(jsonArray);
		res.getWriter().print(jsonArray);
	}
	
	private Timestamp getDate(String requestedDate) throws ParseException {
		log.info("ReminderController: converting to date format");
		DateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy HH:mm:ss", Locale.US); 
        Date date = dateFormat.parse(requestedDate);
        long time = date.getTime();
        return new Timestamp(time);
	}

	private StatusType getStatusType(Timestamp timestamp) {
		log.info("ReminderController: setting status of the reminder");
		StatusType status = null;
		if (new Timestamp(new Date().getTime()).after(timestamp)){
        	status = StatusType.CANCELED;
        } else {
        	status = StatusType.WAITING;
        }
		return status;
	}
}
