package dao;

import java.util.List;

import domain.Role;

public interface RoleDao extends Dao<Role> {
	List<Role> readAll() throws DaoException;

	List<Role> readByUser(Long id) throws DaoException;

	List<Role> readWithoutUser(Long id) throws DaoException;

	Long createUserRole(Long user_id, Long role_id) throws DaoException;

	void deleteUserRole(Long user_id, Long role_id) throws DaoException;
}
