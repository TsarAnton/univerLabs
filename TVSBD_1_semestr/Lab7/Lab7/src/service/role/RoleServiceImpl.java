package service.role;

import java.util.List;

import dao.RoleDao;
import dao.DaoException;
import domain.Role;

import service.ServiceException;

public class RoleServiceImpl implements RoleService {
	private RoleDao roleDao;

	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	@Override
	public List<Role> findAll() throws ServiceException {
		try {
			return roleDao.readAll();
		} catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void save(Role role) throws ServiceException {
		try {
			if(role.getId() != null) {
				roleDao.update(role);
			} else {
				Long id = roleDao.create(role);
				role.setId(id);
			}
		} catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void delete(Long id) throws ServiceException {
		try {
			roleDao.delete(id);
		} catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Role> findByUser(Long id) throws ServiceException {
		try {
			return roleDao.readByUser(id);
		} catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Role> findWithoutUser(Long id) throws ServiceException {
		try {
			return roleDao.readWithoutUser(id);
		} catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void saveUserRole(Long user_id, Long role_id) throws ServiceException {
		try {
			roleDao.createUserRole(user_id, role_id);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void deleteUserRole(Long user_id, Long role_id) throws ServiceException {
		try {
			roleDao.deleteUserRole(user_id, role_id);
		} catch(DaoException e) {
			throw new ServiceException(e);
		}
		
	}
}
