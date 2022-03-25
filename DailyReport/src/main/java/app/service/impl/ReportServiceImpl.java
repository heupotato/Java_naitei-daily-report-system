package app.service.impl;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;

import app.model.Report;
import app.service.ReportService;

public class ReportServiceImpl extends BaseServiceImpl implements ReportService{
	private static final Logger logger = Logger.getLogger(ReportServiceImpl.class);

	@Override
	public Report findById(Serializable key) {
		try {
			return getReportDAO().findById(key);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Report saveOrUpdate(Report entity) {
		try {
			return getReportDAO().saveOrUpdate(entity);
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}

	@Override
	public boolean delete(Report entity) {
		try {
			getReportDAO().delete(entity);
			return true;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<Report> loadReports(String username) {
		try {
			return getReportDAO().loadReports(username); 
		} catch (Exception e) {
			return Collections.<Report>emptyList(); 
		}
	} 
	
	@Override
	public List<Report> loadDrafts(String username) {
		try {
			return getReportDAO().loadDrafts(username); 
		} catch (Exception e) {
			return Collections.<Report>emptyList(); 
		}
	}

	@Override
	public List<Report> loadReportsbyManager(String username, int isApproved) {
		try {
			return getReportDAO().loadReportsbyManager(username, isApproved); 
		} catch (Exception e) {
			return Collections.<Report>emptyList(); 
		}
	}
	
	
}