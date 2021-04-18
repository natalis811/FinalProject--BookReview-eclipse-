package hr.edunova.natalis.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table (name="book")
public class Book extends AbstractEntity {
    
	private String isbn;
	
	@NotEmpty (message ="Book title can not be empty")
	@NotNull (message = "Book title can not be null")
	private String title;
	
	private String published;
	
	@ManyToOne
    private Publisher publisher;
	
	@ManyToOne
    private Category category; 
	
    @ManyToMany
    @JoinTable(
            name = "book_author", 
            joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"), 
            inverseJoinColumns = @JoinColumn(name = "author_id", referencedColumnName = "id"))
    private List<Author> authors;

    public Book() {
		super();
	}
	
	public Book(Long id, String title) {
		super();
		setId(id);
		setTitle(title);
	}
	
	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPublished() {
		return published;
	}

	public void setPublished(String published) {
		this.published = published;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	@Override
	public String toString() {
		return title;
	}   
	
}