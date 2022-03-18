package app.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import app.dao.GenericDAO;
import app.dao.ReportDAO;
import app.model.Report;
import app.service.UserService;


public class ReportDAOImpl extends GenericDAO<Integer, Report> implements ReportDAO {
	private static final Logger logger = Logger.getLogger(ReportDAOImpl.class);
	
	@Autowired
	private UserService userService;

	public ReportDAOImpl() {
		super(Report.class); 
	}
	
	public ReportDAOImpl(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Report> loadReports(String username) {
		int user_id = userService.findByUsername(username).getId(); 
		return getSession().createQuery("from Report where user_id = :user_id").setParameter("user_id", user_id)
				.getResultList(); 
				
	}
	
}