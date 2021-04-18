package hr.edunova.natalis.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;

@Entity
@Table (name="user")
public class User extends Person {
	
	@Email
	private String email;
	
	private String password;
	
	boolean admin;
	
	public User() {
		super();
	}
	
	public User(Long id, String firstName, String lastName) {
		super();
		setId(id);
		setFirstName(firstName);
		setLastName(lastName);
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	@Override
	public String toString() {
		return getFirstName() + " " + getLastName();
	}
	
}