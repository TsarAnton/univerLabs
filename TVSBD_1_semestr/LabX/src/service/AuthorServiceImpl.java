package service;

import java.util.List;

import dao.AuthorDao;
import dao.DaoException;
import domain.Author;

public class AuthorServiceImpl implements AuthorService {
	private AuthorDao authorDao;

	public void setAuthorDao(AuthorDao authorDao) {
		this.authorDao = authorDao;
	}

	@Override
	public List<Author> findAll() throws ServiceException {
		try {
			return authorDao.readAll();
		} catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void save(Author author) throws ServiceException {
		try {
			if(author.getId() != null) {
				authorDao.update(author);
			} else {
				Long id = authorDao.create(author);
				author.setId(id);
			}
		} catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void delete(Long id) throws ServiceException {
		try {
			authorDao.delete(id);
		} catch(DaoException e) {
			throw new ServiceException(e);
		}
	}
}
