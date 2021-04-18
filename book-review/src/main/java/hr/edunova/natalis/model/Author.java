package hr.edunova.natalis.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table (name="author")
public class Author extends Person {
	
	public Author() {
		super();
	}
	
	public Author(Long id, String firstName, String lastName) {
		super();
		setId(id);
		setFirstName(firstName);
		setLastName(lastName);
	}

	@Override
	public String toString() {
		return getFirstName() + " " + getLastName();
	}

}