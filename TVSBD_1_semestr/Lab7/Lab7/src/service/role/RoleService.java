package service.role;

import java.util.List;

import domain.Role;

import service.ServiceException;

public interface RoleService {
	List<Role> findAll() throws ServiceException;

	void save(Role role) throws ServiceException;

	void delete(Long id) throws ServiceException;

	List<Role> findByUser(Long id) throws ServiceException;

	List<Role> findWithoutUser(Long id) throws ServiceException;

	void saveUserRole(Long user_id, Long role_id) throws ServiceException;

	void deleteUserRole(Long user_id, Long role_id) throws ServiceException;
}
