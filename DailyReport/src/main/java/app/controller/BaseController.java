package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;

import app.service.ReportService;
import app.service.UserService;

public class BaseController{
	@Autowired
	protected UserService userService;
	
	@Autowired
	protected ReportService reportService; 
	
	@Autowired
	protected MessageSource messageSource;
	
	public boolean checkAuth(Authentication auth) {
		return !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated(); 
	}
}