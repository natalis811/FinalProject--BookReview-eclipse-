package hr.edunova.natalis.controller;

import java.util.List;

import org.hibernate.CacheMode;

import hr.edunova.natalis.exception.BookException;
import hr.edunova.natalis.model.Book;


public class BookController extends AbstractController<Book> {

	@SuppressWarnings("unchecked")
	@Override
	public List<Book> getData() {
		List<Book> list = session.createQuery("from Book").list();
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


}
