package app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import app.dao.UserDAO;

public class BaseServiceImpl {
	@Autowired
	private UserDAO userDAO;

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
}
