package app.dao;

import java.util.List;

import app.model.Report;

public interface ReportDAO extends BaseDAO<Integer, Report> {
	List<Report> loadReports(String username); 
	
	List<Report> loadDrafts(String username); 

}