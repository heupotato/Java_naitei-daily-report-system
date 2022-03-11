package app.dao;

import java.util.List;

import app.model.User;

public interface UserDAO extends BaseDAO<Integer, User> {
	User findByUsername(String username);

	List<User> loadUsers();
	
	boolean isUser(String username, String password); 

}