package web.app.controllers;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import web.app.configuration.Generator;
import web.app.configuration.RoleType;
import web.app.entities.UserRole;
import web.app.entities.Users;
import web.app.sender.EmailSender;
import web.app.sender.SkypeSender;
import web.app.service.UserRoleService;
import web.app.service.UserService;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.skype.SkypeException;

@Controller
public class UserController {
	private static Logger log = Logger.getLogger("file");

	@Autowired
	private UserService userService;

	@Autowired
	private UserRoleService userRoleService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView authorize(@RequestParam(value = "error", required = false) String error, @RequestParam(value = "logout", required = false) String logout) {
		log.info("UserController: showing login form");
		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username or password!");
		}
		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName("pages/login");
		return model;
	}
	
	@RequestMapping({ "/index", "/", "" })
	public String showMainPage(ModelMap model) throws Exception {
		log.info("UserController: showing index.html page");
		Users user = userService.getActiveUser();
		if (user == null){
			return "redirect:/login";
		}
		model.addAttribute("user", user);
		return "/pages/index";
	}
	
	@RequestMapping(value = {"/username/validation", "/email/validation"}, method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void processAJAXUserValidation(@RequestBody String data, HttpServletRequest request, HttpServletResponse response) throws IOException {
		log.info("UserController: check if username or email exists in database");
		JsonObject json = new JsonParser().parse(data).getAsJsonObject();
        String param = json.get("param").getAsString();
		
		boolean isExists = false;
		String servletPath = request.getServletPath();
		if(servletPath.contains("username")){
			isExists = userService.findByName(param) != null ? true : false;
		} else {
			isExists = userService.findByEmail(param) != null ? true : false;
		}
		
		HashMap<String, Object> JSONROOT = new HashMap<String, Object>();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		response.setContentType("json");
		JSONROOT.put("Result", "OK");
		JSONROOT.put("result", isExists);
		String jsonArray = gson.toJson(JSONROOT);
		
		log.info(jsonArray);
		response.getWriter().print(jsonArray);
	}
	
	@RequestMapping(value = {"/skype/isSkypeContactAdded"}, method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void processAJAXSkypeValidation(@RequestBody String data, HttpServletRequest request, HttpServletResponse response) throws IOException, SkypeException {
		log.info("UserController: check if user is in skype contact list");
        JsonObject json = new JsonParser().parse(data).getAsJsonObject();
        String skypeName = json.get("skypeName").getAsString();
		
		String message = null;
		if (!SkypeSender.isContactAdded(skypeName)) {
			SkypeSender.addContact(skypeName);
			message = "Please, add reminder.agent to your skype contact list";
		}
		
		HashMap<String, Object> JSONROOT = new HashMap<String, Object>();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		response.setContentType("json");
		JSONROOT.put("Result", "OK");
		JSONROOT.put("result", message);
		String jsonArray = gson.toJson(JSONROOT);
		
		log.info(jsonArray);
		response.getWriter().print(jsonArray);
	}
	
	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String showRegistrationPage() throws Exception {
		log.info("UserController: showing registration form");
		return "/pages/registration";
	}
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String createUser(@ModelAttribute("userAttribute") Users user, BindingResult bindingResult) throws Exception {
		log.info("UserController: request to register new user");
		user.setEnabled(true);
		user.setPassword(Generator.encryptPass(user.getPassword()));
		userService.addUser(user);
		EmailSender emailAgent = new EmailSender();
		emailAgent.registration(user);
		userRoleService.setRole(new UserRole(user.getId(), RoleType.ROLE_USER.getType()));
		log.info("UserController: user created and saved, going to login page");
		return "redirect:/login";
	}

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public ModelAndView getUserProfile(HttpServletRequest req, HttpServletResponse res) throws Exception {
		log.info("UserController: showing profile page");
		Users user = userService.getActiveUser();
		ModelAndView view = new ModelAndView("pages/profile");
		view.addObject("id", user.getId());
		view.addObject("username", user.getUsername());
		view.addObject("email", user.getEmail());
		view.addObject("skype", user.getSkype());
		view.addObject("phone", user.getPhone().replace(" ", ""));
		view.addObject("role", user.getRole());
		view.addObject("enabled", user.isEnabled());
		view.addObject("pass", "");
		return view;
	}

	@RequestMapping(value = "/profile", method = RequestMethod.POST)
	public ModelAndView updateUserProfile(@ModelAttribute("userAttribute") Users user, BindingResult bindingResult,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		log.info("UserController: saving user profile");
		user.setPassword(Generator.encryptPass(user.getPassword()));
		userService.updateUser(user);
		return getUserProfile(req, res);
	}

	@RequestMapping(value = "/contact.html", method = RequestMethod.GET)
	public String showContactPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("UserController: showing contact page");
		return "/pages/contact";
	}
	
	@RequestMapping(value = "/contact.html", method = RequestMethod.POST)
	public void sendComment(HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("UserController: send contact mesage");
		String subject = request.getParameter("topic");
		String comment = request.getParameter("message");
		EmailSender agent = new EmailSender();
		agent.sendComment(userService.getActiveUser(), subject, comment);
	}
}