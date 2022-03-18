package app.controller;

import java.util.Locale;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import app.service.ReportService;

@Controller
public class ReportController extends BaseController{
	private static final Logger logger = Logger.getLogger(HomeController.class);
	
	@RequestMapping(value = "/reports")
	public ModelAndView showListReports() {
		logger.info("Report List Page"); 
		ModelAndView model = new ModelAndView("views/reports/report-list");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = ""; 
		if (checkAuth(auth)) {
			username =  auth.getName();
			model.addObject("currentUser", username);
			model.addObject("noReportAlert", messageSource.getMessage("report.noreport", null, Locale.US)); 
		}
		else return new ModelAndView("redirect:/login");
		
		model.addObject("reports", reportService.loadReports(username)); 
		return model; 
	}
}