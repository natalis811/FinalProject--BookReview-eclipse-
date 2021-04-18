package hr.edunova.natalis.controller;

import java.util.List;

import org.hibernate.CacheMode;

import hr.edunova.natalis.exception.BookException;
import hr.edunova.natalis.model.Book;
import hr.edunova.natalis.model.Publisher;

public class PublisherController extends AbstractController<Publisher> {

	@SuppressWarnings("unchecked")
	@Override
	public List<Publisher> getData() {
			List<Publisher> list = session.createQuery("from Publisher").list();
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
