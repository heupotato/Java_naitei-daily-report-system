package app.service;

import java.util.List;

import app.model.User;

public interface UserService extends BaseService<Integer, User> {
	boolean deleteUser(Integer id);

	User findByUsername(String username);
	
	List<User> loadUsers();
	
	boolean isUser(String username, String password); 
	
}
