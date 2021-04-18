package hr.edunova.natalis.model;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@MappedSuperclass
public abstract class Person extends AbstractEntity {
	
	@NotEmpty (message = "First name can not be empty")
	@NotNull (message = "First name can not be empty ")
	private String firstName;
	
	@NotEmpty (message = "Last name can not be empty")
	@NotNull (message = "Last name can not be empty ")
	private String lastName;
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}