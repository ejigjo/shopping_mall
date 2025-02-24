package service.impl;


import at.favre.lib.crypto.bcrypt.BCrypt;
import dao.UserDao;
import dao.impl.UserDaoImpl;
import model.User;
import service.UserService;



public class UserServiceImpl implements UserService{
	
	private UserDao userDao = new UserDaoImpl();
	
	public boolean registerUser(User user) {
		String password = user.getPassword();
		String hashpw =  BCrypt.withDefaults().hashToString(12, password.toCharArray());
		user.setPassword(hashpw);
		return userDao.insertUser(user);
	}

	@Override
	public User getUserByUsername(String username) {
		return userDao.getUserByUsername(username);
	}
}
