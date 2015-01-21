package lookup.repository;

import java.util.List;

import lookup.domain.User;

public interface UserRepository {

	/*
	 * Get all Users 
	 * */
	public List<User> getAllUsers();
	
	/*
	 * Get users by name
	 * */
	public List<User> getUserByName(String name);
	
	/*
	 * Get user by login ID
	 * */
	public List<User> getUserByLoginID(String loginID);
	
	/*
	 * Get user by id
	 * */
	public  User getUserById(int id);
	
	/*
	 * Add a new User
	 * */
	public int saveUser(User user, String password);
	
	/*
	 * Update existing User Details
	 * */
	public int updateUser(User user);
	 
	/*
	 * Authenticate user and return details if valid
	 * */
	public User autheticateUser(String loginID, String password);
}
