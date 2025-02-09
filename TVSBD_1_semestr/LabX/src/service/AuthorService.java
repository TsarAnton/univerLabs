package service;

import java.util.List;

import domain.Author;

public interface AuthorService {
	List<Author> findAll() throws ServiceException;

	void save(Author author) throws ServiceException;

	void delete(Long id) throws ServiceException;
}
