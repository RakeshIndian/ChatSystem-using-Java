package service;

import java.sql.SQLException;
import java.util.List;

import domain.User;

public class UserService {
	private static UserDAO userDao = new UserDAO();
	public boolean registerUser(User user) throws ClassNotFoundException, SQLException {
		userDao.insertUser(user);
		return true;
	}
	public List<User> getUsersByName(String name) throws ClassNotFoundException, SQLException{
		return userDao.getUserByName(name);
	}
	public List<User> getUserByPhone(Long phone) throws ClassNotFoundException, SQLException{
		return userDao.getUserByPhone(phone);
	}
	public User getUserByEmail(String email) throws ClassNotFoundException, SQLException {
		return userDao.getUserByEmail(email);
	}
}
