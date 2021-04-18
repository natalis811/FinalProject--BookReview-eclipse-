package hr.edunova.natalis.controller;

import java.util.List;

import org.hibernate.CacheMode;
import org.mindrot.jbcrypt.BCrypt;

import hr.edunova.natalis.exception.BookException;
import hr.edunova.natalis.model.User;

public class UserController extends AbstractController<User> {

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getData() {
		List<User> list = session.createQuery("from User").list();
        session.setCacheMode(CacheMode.IGNORE);
        return list; 
	}

	@Override
	protected void controlCreate() throws BookException {
		
	}

	@Override
	protected void controlUpdate() throws BookException {
		
	}

	@Override
	protected void controlDelete() throws BookException {
		
	}
	
	public User findUser(String email) {
        try {
            User user = (User) session
                    .createQuery("from User u where u.email=:email")
                    .setParameter("email", email)
                    .getSingleResult();
            return user;
        } catch (javax.persistence.NoResultException e) {
            return null;
        }
    }
	
	public User register(String firstName, String lastName, String email, char[] password, char[] passwordConfirm) throws BookException {
		if (!new String(password).equals(new String(passwordConfirm))) {
            throw new BookException("The password and confirmation password do not match.");
        }
		
		User user = findUser(email);
        if (user != null) {
        	throw new BookException("Username alredy exists.");
        }  
        
        user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(BCrypt.hashpw(new String (password), BCrypt.gensalt()));
        
        setEntity(user);
        user = create();
        
        return user;
	}
	
}