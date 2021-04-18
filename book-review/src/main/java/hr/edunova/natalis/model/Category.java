package hr.edunova.natalis.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table (name = "category")
public class Category extends AbstractEntity {

	@NotNull (message="Category name is obligatory(category is null)")
	@NotEmpty (message="Category name can not be empty")
	private String name;

	public Category() {
		super();
	}
	
	public Category(Long id, String name) {
		super();
		setId(id);
		setName(name);
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		if (getName() == null || getName().trim().isEmpty()) {
			return "[Category Name is not defined]";
		}
		return getName();
	}

}