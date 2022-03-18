package app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import app.dao.ReportDAO;
import app.dao.UserDAO;

public class BaseServiceImpl {

	@Autowired
	private UserDAO userDAO;
	@Autowired
	private ReportDAO reportDAO; 

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public ReportDAO getReportDAO() {
		return reportDAO;
	}

	public void setReportDAO(ReportDAO reportDAO) {
		this.reportDAO = reportDAO;
	}
}
