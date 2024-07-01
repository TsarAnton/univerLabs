package service.user;

import java.util.List;

import domain.User;

import service.ServiceException;

public interface UserService {
	List<User> findAll() throws ServiceException;

	void save(User user) throws ServiceException;

	void delete(Long id) throws ServiceException;

	List<User> findByRole(Long id) throws ServiceException;

	List<User> findWithoutRole(Long id) throws ServiceException;
}
