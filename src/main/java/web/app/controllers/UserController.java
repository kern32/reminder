package web.app.controllers;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
/*import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;*/
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import web.app.configuration.Generator;
import web.app.configuration.RoleType;
import web.app.entities.UserRole;
import web.app.entities.Users;
import web.app.service.ReminderService;
import web.app.service.UserRoleService;
import web.app.service.UserService;

@Controller
@RequestMapping(value = "/app/")
public class UserController {
	private static final Logger LOG = Logger.getLogger(UserController.class);
	static Users user;

	@Autowired
	private UserService userService;

	@Autowired
	private ReminderService reminderService;
	
	@Autowired
	private UserRoleService userRoleService;

	@RequestMapping(value = "/username/validation.html**", method = RequestMethod.GET)
	public @ResponseBody String processAJAXUsernameValidation(
			@RequestParam String text) {
			String existing_user= "user exists";
			// Process the request
			// Prepare the response string
			return existing_user;
		}
	
	@RequestMapping(value = "/email/validation.html**", method = RequestMethod.GET)
	public @ResponseBody String processAJAXEmailValidation(
			@RequestParam String text) {
			String existing_email = "email exists";
			// Process the request
			// Prepare the response string
			return existing_email;
		}
	
	@RequestMapping({ "/index.html", "/", "" })
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) throws Exception {
		/*LOG.info("going to index.html");
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		user = userService.findByName(userName);*/
		ModelAndView view = new ModelAndView("/pages/index");
		/*request.getSession().setAttribute("count", userService.countReminders(user.getName()));
		request.getSession().setAttribute("user", user);*/
		return view;
	}

	@RequestMapping(value = "/messages.html", method = RequestMethod.GET)
	public ModelAndView messages(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LOG.info("login page - GET request");
		return new ModelAndView("pages/messages");
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName("pages/login");

		return model;

	}


	@RequestMapping(value = "/registration.html", method = RequestMethod.GET)
	public ModelAndView getRegistration(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LOG.info("get request registration");
		return new ModelAndView("pages/registration");
	}

	@RequestMapping(value = "/registration.html", method = RequestMethod.POST)
	public ModelAndView postRegistration(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String name = request.getParameter("req_name");
		Users user = userService.findByName(name);
		if (user != null) {
			LOG.info("user with this email is already exists going to registration.html");
			request.setAttribute("exists", true);
			return getRegistration(request, response);
		}
		String email = request.getParameter("req_email");
		String phone = request.getParameter("req_phone").replaceAll("\\D", "");
		String skype = request.getParameter("req_skype");
		String pass = request.getParameter("req_psw");
		String encryptPsw = Generator.encryptPass(pass);
		// Mailer.sendMail(name, email, pass);
		user = new Users();
		user.setName(name);
		user.setEmail(email);
		user.setPhone(phone);
		user.setSkype(skype);
		user.setPsw(encryptPsw);
		userService.saveUser(user);	
		userRoleService.setRole(new UserRole(user.getId(), RoleType.ROLE_USER.getType()));
		
		LOG.info("user created and saved, going to index.html");
		return index(request, response);
	}

	@RequestMapping(value = "/profile.html", method = RequestMethod.GET)
	public ModelAndView getProfile(HttpServletRequest req, HttpServletResponse res) throws Exception {
		LOG.info("going to profile.html");
		ModelAndView view = new ModelAndView("pages/profile");
		/*view.addObject("username", user.getName());
		view.addObject("email", user.getEmail());
		view.addObject("skype", user.getSkype());
		view.addObject("phone", user.getPhone().replace(" ", ""));
		view.addObject("pass", Generator.decrypt(user.getPsw(), user.getKey()));*/
		return view;
	}

	@RequestMapping(value = "/profile.html", method = RequestMethod.POST)
	public ModelAndView postProfile(HttpServletRequest req, HttpServletResponse res) throws Exception {
		LOG.info("saving profile.html");
		user.setEmail(req.getParameter("req_email"));
		user.setPhone(req.getParameter("req_phone"));
		user.setSkype(req.getParameter("req_skype"));
		String key = UUID.randomUUID().toString().replaceAll("-", "");
		user.setPsw(Generator.encryptPass(req.getParameter("req_psw")));
		userService.updateUser(user);
		return getProfile(req, res);
	}

	@RequestMapping("/404.html")
	public ModelAndView pageNotFound(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LOG.info("going to 404.html");
		return new ModelAndView("/pages/errors/404");
	}

	@RequestMapping("/401.html")
	public ModelAndView unauthorized(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LOG.info("going to 401.html");
		return new ModelAndView("/pages/errors/401");
	}

	@RequestMapping("/403.html")
	public ModelAndView forbidden(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LOG.info("going to 403.html");
		return new ModelAndView("/pages/errors/403");
	}
	
	@RequestMapping("/contact.html")
	public ModelAndView contact(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LOG.info("going to contact.html");
		return new ModelAndView("/pages/contact");
	}

}