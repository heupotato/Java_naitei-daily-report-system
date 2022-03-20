package app.controller;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController extends BaseController{
	private static final Logger logger = Logger.getLogger(HomeController.class);

	@RequestMapping(value = "/")
	public ModelAndView index(HttpSession session) {
		logger.info("Home page");
		String url = checkAuth("views/dailyreport/index");
		ModelAndView model = new ModelAndView(url);
		if (url.contains("redirect")) return model;
		model.addObject("currentUser", session.getAttribute("currentUser"));
		return model;
	}
	
	@RequestMapping("/login")
	public String login(@RequestParam(value = "error", required = false) final String error, final Model model) {
		logger.info("login page");
		if (error != null) {
			model.addAttribute("css", "error");
			model.addAttribute("msg", messageSource.getMessage("login.fail", null, Locale.US));
		}
		return "/views/users/login";
	}
	
	@RequestMapping("logout")
	public String logout(final Model model) {
		logger.info("login page");
		model.addAttribute("msg", messageSource.getMessage("logout.success", null, Locale.US));
		return "/views/users/login";
	}

}
