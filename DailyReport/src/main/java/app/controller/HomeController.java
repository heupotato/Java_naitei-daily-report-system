package app.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import app.service.UserService;

@Controller
public class HomeController {
	private static final Logger logger = Logger.getLogger(HomeController.class);

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/")
	public ModelAndView index() {
		logger.info("Home page");
		ModelAndView model = new ModelAndView("views/dailyreport/index");
		model.addObject("abc", userService.loadUsers());
		return model;
	}

}
