package app.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import app.model.User;
import app.service.UserService;
import app.service.impl.UserServiceImpl;

@Controller
public class MainController {
	private static final Logger logger = Logger.getLogger(MainController.class);
	
	@RequestMapping(value = "/")
	public ModelAndView index() {
		logger.info("Home page");
		ModelAndView model = new ModelAndView("views/dailyreport/index");
		return model;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response) {
		logger.info("Request login page"); 
		ModelAndView model  = new ModelAndView("views/users/login"); 
		User loginUser = new User(); 
		model.addObject(loginUser); 
		logger.info("Open login page"); 
		return model; 
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView executeLogin(HttpServletRequest request, HttpServletResponse response,
            @ModelAttribute("loginBean") User loginBean) {
        ModelAndView view = null;
        UserServiceImpl userService = new UserServiceImpl(); 
        loginBean.display(); 
        if (userService.isUser(loginBean.getUsername(), loginBean.getPassword())) {
            view = new ModelAndView("views/dailyreport/index");
            view.addObject("loggedInUser", loginBean.getUsername());
           
        } else {
            view = new ModelAndView("views/users/login");
            view.addObject("message", "Invalid username or password!");
        }
        return view;
    }
	
	
}
