package dao;

import java.util.List;

import domain.User;

public interface UserDao extends Dao<User> {
	List<User> readAll() throws DaoException;

	List<User> readByRole(Long id) throws DaoException;

	List<User> readWithoutRole(Long id) throws DaoException;
}
