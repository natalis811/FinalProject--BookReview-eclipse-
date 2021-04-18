package hr.edunova.natalis.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table (name = "review")
public class Review extends AbstractEntity {
	
	private String text;
	
	private Integer rating;
	
	private Date date;
	
	@ManyToOne
    private Book book; 
    
	@ManyToOne
    private User user;
	
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public Integer getRating() {
		return rating;
	}
	
	public void setRating(Integer rating) {
		this.rating = rating;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
		
	}
	public Book getBook() {
		return book;
	}
	
	public void setBook(Book book) {
		this.book = book;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	} 
	public String toString() {
		return getBook().getTitle()+ " - " +" " + getUser().getFirstName()+ " " + getUser().getLastName();
		
	}
    
}