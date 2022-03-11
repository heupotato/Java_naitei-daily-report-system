package app.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;

import app.dao.GenericDAO;
import app.dao.UserDAO;
import app.model.User;

public class UserDAOImpl extends GenericDAO<Integer, User> implements UserDAO {
	private static final Logger logger = Logger.getLogger(UserDAOImpl.class);

	public UserDAOImpl() {
		super(User.class);
	}

	public UserDAOImpl(SessionFactory sessionfactory) {
		setSessionFactory(sessionfactory);
	}

	@Override
	public User findByUsername(String username) {
		logger.info("username: " + username);
		return (User) getSession().createQuery("FROM user where username = ?").setParameter(0, username)
				.getSingleResult();
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> loadUsers() {
		return getSession().createQuery("from user").getResultList();
	}

	@Override
	public boolean isUser(String username, String password) {
		logger.info("Validate User: " + username);
		User user = this.findByUsername(username); 
		if (user != null && user.getPassword().equals(password)) {
            return true;
        }
        return false;
	}

}
