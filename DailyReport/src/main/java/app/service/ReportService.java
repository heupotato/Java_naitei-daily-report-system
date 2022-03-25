package app.service;

import java.util.List;

import app.model.Report;

public interface ReportService extends BaseService<Integer, Report> {
	List<Report> loadReports(String username);
	
	List<Report> loadDrafts(String username);
	
	List<Report> loadReportsbyManager(String username, int isApproved); 
	
}