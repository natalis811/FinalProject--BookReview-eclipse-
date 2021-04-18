package hr.edunova.natalis.util;

import org.hibernate.Session;
import org.mindrot.jbcrypt.BCrypt;

import hr.edunova.natalis.model.User;

public class InitData {

	public void init() {
        Session session = HibernateUtil.getSession();
        try {
        	session
                .createQuery("from User u where u.email=:email")
                .setParameter("email", "natalis811@gmail.com")
                .getSingleResult();
        } catch (Exception e) {
        	User user = new User();
        	user.setFirstName("Natalija");
        	user.setLastName("Santek");
        	user.setEmail("natalis811@gmail.com");
        	user.setPassword(BCrypt.hashpw("edunova", BCrypt.gensalt()));
        	user.setAdmin(true);
        	
        	session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        }
	}
	
}