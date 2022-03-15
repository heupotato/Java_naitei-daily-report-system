package app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

//import app.dao.StudentDAO;
import app.dao.UserDAO;

public class BaseServiceImpl {
//	@Autowired
//	protected StudentDAO studentDAO;

	@Autowired
	private UserDAO userDAO;

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

//	public StudentDAO getStudentDAO() {
//		return studentDAO;
//	}
//
//	public void setStudentDAO(StudentDAO studentDAO) {
//		this.studentDAO = studentDAO;
//	}
}
