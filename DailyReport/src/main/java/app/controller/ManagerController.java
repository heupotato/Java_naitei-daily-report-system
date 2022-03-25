package app.controller;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import app.model.Report;

@Controller("ManagerController")
@RequestMapping("/admin/reports")
public class ManagerController extends BaseController{
	private static final Logger logger = Logger.getLogger(ManagerController.class); 
	private static final int IS_APPROVED = 1; 
	private static final int NOT_APPROVED = 0; 
	
	@RequestMapping(value="/approved")
	public ModelAndView showApprovedReport(HttpSession session) {
		logger.info("MANAGER - APPROVED REPORT");
		String url = checkAuth("views/admin/report-list");
		ModelAndView model = new ModelAndView(url);
		if (url.contains("redirect")) return model;
		String username = session.getAttribute("currentUser").toString(); 
		model.addObject("title", messageSource.getMessage("report.manager.title.approved", null, Locale.US)); 
		model.addObject("currentUser", username);
		model.addObject("noReportAlert", messageSource.getMessage("report.manager.noreport.approved", null, Locale.US)); 
		List<Report> reportList = Collections.<Report>emptyList();
		try {
			reportList = reportService.loadReportsbyManager(username, IS_APPROVED); 
		}catch (Exception e) {
			logger.error(e); 
		}
		model.addObject("reports", reportList); 
		model.addObject("isManager", session.getAttribute("isManager")); 
		return model; 
	}
	
	@RequestMapping(value= "/pending")
	public ModelAndView showPendingReport(HttpSession session) {
		logger.info("MANAGER - PENDING REPORT");
		String url = checkAuth("views/admin/report-list");
		ModelAndView model = new ModelAndView(url);
		if (url.contains("redirect")) return model;
		String username = session.getAttribute("currentUser").toString(); 
		model.addObject("title", messageSource.getMessage("report.manager.title.pending", null, Locale.US)); 
		model.addObject("currentUser", username);
		model.addObject("noReportAlert", messageSource.getMessage("report.manager.noreport.pending", null, Locale.US)); 
		List<Report> reportList = Collections.<Report>emptyList();
		try {
			reportList = reportService.loadReportsbyManager(username, NOT_APPROVED); 
		}catch (Exception e) {
			logger.error(e); 
		}
		model.addObject("reports", reportList); 
		model.addObject("isManager", session.getAttribute("isManager")); 
		model.addObject("pending", true); 
		return model; 
	}
	
}