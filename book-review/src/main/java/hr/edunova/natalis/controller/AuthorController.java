package hr.edunova.natalis.controller;

import java.util.List;

import org.hibernate.CacheMode;

import hr.edunova.natalis.exception.BookException;
import hr.edunova.natalis.model.Author;
import hr.edunova.natalis.model.Publisher;

public class AuthorController extends AbstractController<Author> {

	@Override
	public List<Author> getData() {
		List<Author> list = session.createQuery("from Author").list();
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
		// TODO Auto-generated method stub
		
	}

}
