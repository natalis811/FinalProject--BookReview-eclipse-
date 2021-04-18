package hr.edunova.natalis.controller;

import org.mindrot.jbcrypt.BCrypt;

import hr.edunova.natalis.model.User;

public class AuthorizationController {
	
	private UserController userController;
	
	public AuthorizationController() {
		super();
		userController = new UserController();
	}
	
	public User login(String email, char[] password) throws Exception {
		if (email == null || email.trim().length() == 0) {
			throw new Exception("Username cannot be empty");
		}
		
		User user = userController.findUser(email);
		if (user == null) {
			throw new Exception("Unknown user");
		}
		
		if (BCrypt.checkpw(new String(password), user.getPassword())) {
			return user;
		} else {
			throw new Exception("Wrong password");
		}
	}
	
}