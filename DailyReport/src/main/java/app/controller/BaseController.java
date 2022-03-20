package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import app.service.ReportService;
import app.service.UserService;

public class BaseController{
	@Autowired
	protected UserService userService;
	
	@Autowired
	protected ReportService reportService; 
	
	@Autowired
	protected MessageSource messageSource;
	
	public String checkAuth(String url) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication(); 
		if ((auth instanceof AnonymousAuthenticationToken) || !auth.isAuthenticated()) 
			return "redirect:/login"; 
		return url;
	}
}