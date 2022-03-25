package app.controller;

import java.util.Locale;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import app.model.Report;
import app.model.User;
import app.service.DateUtils;

@Controller
public class ReportController extends BaseController{
	private static final Logger logger = Logger.getLogger(HomeController.class);
	private static final int DELETE_TRUE = 1; 
	
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
		model.addObject("title", messageSource.getMessage("report.title.list", null, Locale.US)); 
		model.addObject("draft", false); 
		model.addObject("isManager", session.getAttribute("isManager")); 
		return model; 
	}
	
	@RequestMapping(value = "/reports/drafts")
	public ModelAndView showListDrafts(HttpSession session) {
		logger.info("Drafts Page"); 
		String url = checkAuth("views/reports/report-list");
		ModelAndView model = new ModelAndView(url);
		if (url.contains("redirect")) return model;
		String username = session.getAttribute("currentUser").toString(); 
		model.addObject("currentUser", session.getAttribute("currentUser"));
		
		model.addObject("noDraft", messageSource.getMessage("report.nodraft", null, Locale.US)); 
		model.addObject("title", messageSource.getMessage("report.title.draft", null, Locale.US)); 
		model.addObject("reports", reportService.loadDrafts(username)); 
		model.addObject("draft", true); 
		model.addObject("isManager", session.getAttribute("isManager")); 
		return model; 
	}
	
	@RequestMapping(value = "reports/{id}")
	public ModelAndView showReport(@PathVariable("id") int id, HttpSession session) {
		logger.info("Report Detail" + id); 
		String url = checkAuth("views/reports/report-detail");
		ModelAndView model = new ModelAndView(url);
		if (url.contains("redirect")) return model;
		
		Report report = reportService.findById(id); 
		if (report == null || report.getIsDeleted() != 0) {
			model.addObject("error", messageSource.getMessage("report.notFound", null, Locale.US));
			if (report.getIsDeleted() != 0) model.addObject("report", reportService.findById(id)); 
		}
		else {
			model.addObject("report", reportService.findById(id)); 
			model.addObject("reportNoIssue", messageSource.getMessage("report.content.noIssue", null, Locale.US)); 
			model.addObject("reportNoPlan", messageSource.getMessage("report.content.noPlan", null, Locale.US)); 
		}
		model.addObject("currentUser", session.getAttribute("currentUser"));
		model.addObject("isManager", session.getAttribute("isManager")); 
		return model; 
	}
	
	@RequestMapping(value = "reports/new")
	public ModelAndView createReport(HttpSession session) {
		logger.info("Create Report");
		
		String url = checkAuth("views/reports/new-report");
		ModelAndView model = new ModelAndView(url);
		if (url.contains("redirect")) return model;
		
		Report report = new Report(); 
		report.setCreatedAt(DateUtils.getToday());
		
		report.setActure(messageSource.getMessage("report.acture.default", null, Locale.US));
		
		model.addObject("currentUser", session.getAttribute("currentUser")); 
		model.addObject("report", report); 
		model.addObject("title", messageSource.getMessage("report.title.new", null, Locale.US)); 
		model.addObject("isManager", session.getAttribute("isManager")); 
		return model; 
	}
	
	@RequestMapping(value = "reports/{id}/update")
	public ModelAndView updateReport(@PathVariable("id") int id, HttpSession session) {
		logger.info("Update report"); 
		String url = checkAuth("views/reports/new-report");
		ModelAndView model = new ModelAndView(url);
		if (url.contains("redirect")) return model;
		Report report = reportService.findById(id);
		
		if (report == null || report.getIsDeleted() != 0) {
			model.addObject("error", messageSource.getMessage("report.notFound", null, Locale.US));
			if (report.getIsDeleted() != 0) model.addObject("report", reportService.findById(id)); 
		}
		else {
			model.addObject("report", reportService.findById(id)); 
		}
		model.addObject("title", messageSource.getMessage("report.title.update", null, Locale.US)); 
		model.addObject("currentUser", session.getAttribute("currentUser"));
		model.addObject("isManager", session.getAttribute("isManager")); 
		return model; 
	}
	
	@RequestMapping(value = "/reports", method = RequestMethod.POST)
	public String saveOrUpdate(@Valid @ModelAttribute Report report, BindingResult bindingReport,
			final RedirectAttributes redirectAttributes, Model model, HttpSession session) {
		logger.info("POST - Save report");
		if (bindingReport.hasErrors()) {
			logger.error("Binding Error" + bindingReport.getAllErrors()); 
			return "views/reports/new-report";
		}
		
		
		report.setLastUpdatedAt(DateUtils.getToday());  
		User user = userService.findByUsername(session.getAttribute("currentUser").toString()); 
		report.setUser(user); 
		
		Report newReport = reportService.saveOrUpdate(report);	
		if (newReport == null) {
			redirectAttributes.addFlashAttribute("css", "danger");
			redirectAttributes.addFlashAttribute("msg", messageSource.getMessage("report.create.fail", null, Locale.US));
		} else {
			redirectAttributes.addFlashAttribute("css", "success");
			redirectAttributes.addFlashAttribute("msg", messageSource.getMessage("report.create.success", null, Locale.US));
		}
		
		return "redirect:/reports/"+ newReport.getId();
	}
	
	@RequestMapping(value = "/reports/{id}/undraft", method = RequestMethod.POST)
	public ModelAndView undraftReport(@PathVariable("id") int id) {
		logger.info("Undraft report"); 
		String url = checkAuth("redirect:/reports/" + id);
		ModelAndView model = new ModelAndView(url);
		if (url.contains("login")) return model;
		Report report = reportService.findById(id);
		try {
			report.setIsDraft(false); 
			reportService.saveOrUpdate(report);	
		}
		catch (Exception e) {
			logger.error(e); 
		}
		return model; 
	}
	
	@RequestMapping(value = "/reports/{id}/delete", method = RequestMethod.POST)
	public ModelAndView deleteReport(@PathVariable("id") int id) {
		logger.info("Delete report"); 
		String url = checkAuth("redirect:/reports/" + id);
		ModelAndView model = new ModelAndView(url);
		if (url.contains("login")) return model;
		Report report = reportService.findById(id);
		try {
			report.setIsDeleted(DELETE_TRUE); 
			reportService.saveOrUpdate(report);	
		}catch (Exception e) {
			logger.error(e);
		}
		
		return model; 
	}
}