package app.controller;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import app.model.Report;
import app.service.ReportService;

@Controller
public class ReportController extends BaseController{
	private static final Logger logger = Logger.getLogger(HomeController.class);
	
	@RequestMapping(value = "/reports")
	public ModelAndView showListReports(HttpSession session) {
		logger.info("Report List Page"); 
		String url = checkAuth("views/reports/report-list");
		ModelAndView model = new ModelAndView(url);
		if (url.contains("redirect")) return model;
		String username = session.getAttribute("currentUser").toString(); 
		model.addObject("currentUser", session.getAttribute("currentUser"));
		
		model.addObject("noReportAlert", messageSource.getMessage("report.noreport", null, Locale.US)); 
		model.addObject("reports", reportService.loadReports(username)); 
		return model; 
	}
	
	@RequestMapping(value = "report/{id}")
	public ModelAndView showReport(@PathVariable("id") int id, HttpSession session) {
		logger.info("Report Detail" + id); 
		String url = checkAuth("views/reports/report-detail");
		ModelAndView model = new ModelAndView(url);
		if (url.contains("redirect")) return model;
		
		Report report = reportService.findById(id); 
		if (report == null) {
			model.addObject("error", messageSource.getMessage("report.notFound", null, Locale.US));
		}
		else {
			model.addObject("report", reportService.findById(id)); 
			model.addObject("reportNoIssue", messageSource.getMessage("report.content.noIssue", null, Locale.US)); 
			model.addObject("reportNoPlan", messageSource.getMessage("report.content.noPlan", null, Locale.US)); 
		}
		model.addObject("currentUser", session.getAttribute("currentUser"));
		return model; 
	}
	
}