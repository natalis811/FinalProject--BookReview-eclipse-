package hr.edunova.natalis.controller;

import java.util.List;

import org.hibernate.CacheMode;

import hr.edunova.natalis.exception.BookException;
import hr.edunova.natalis.model.Book;
import hr.edunova.natalis.model.Review;
import hr.edunova.natalis.model.User;

public class ReviewController extends AbstractController<Review> {

	@SuppressWarnings("unchecked")
	@Override
	public List<Review> getData() {
		List<Review> list = session.createQuery("from Review").list();
		session.setCacheMode(CacheMode.IGNORE);
		return list;
	}
	public Review findReview(Book book, User user) {
		try {
			Review review = (Review) session
					.createQuery("from Review r where r.book=:book and r.user=:user")
					.setParameter("book", book)
					.setParameter("user", user)
					.getSingleResult();
			return review;
		} catch (javax.persistence.NoResultException e) {
			return null;
		}
	}

	@Override
	protected void controlCreate() throws BookException {
		// TODO Auto-generated method stub

	}

	@Override
	protected void controlUpdate() throws BookException {
		// TODO Auto-generated method stub

	}

	@Override
	protected void controlDelete() throws BookException {
		// TODO Auto-generated method stub

	}

}
