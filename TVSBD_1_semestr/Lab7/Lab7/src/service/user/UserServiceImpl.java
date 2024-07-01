package service.user;

import java.util.List;

import dao.UserDao;
import dao.DaoException;
import domain.User;

import service.ServiceException;

public class UserServiceImpl implements UserService {
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public List<User> findAll() throws ServiceException {
		try {
			return userDao.readAll();
		} catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void save(User user) throws ServiceException {
		try {
			if(user.getId() != null) {
				userDao.update(user);
			} else {
				Long id = userDao.create(user);
				user.setId(id);
			}
		} catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void delete(Long id) throws ServiceException {
		try {
			userDao.delete(id);
		} catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<User> findByRole(Long id) throws ServiceException {
		try {
			return userDao.readByRole(id);
		} catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<User> findWithoutRole(Long id) throws ServiceException {
		try {
			return userDao.readWithoutRole(id);
		} catch(DaoException e) {
			throw new ServiceException(e);
		}
	}
}
