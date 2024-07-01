package service.break1;

import java.util.List;

import dao.BreakDao;
import dao.DaoException;
import domain.Break;

import service.ServiceException;

public class BreakServiceImpl implements BreakService {
	private BreakDao breakDao;

	public void setBreakDao(BreakDao breakDao) {
		this.breakDao = breakDao;
	}

	@Override
	public List<Break> findAll() throws ServiceException {
		try {
			return breakDao.readAll();
		} catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void save(Break break1) throws ServiceException {
		try {
			if(break1.getId() != null) {
				breakDao.update(break1);
			} else {
				Long id = breakDao.create(break1);
				break1.setId(id);
			}
		} catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void delete(Long id) throws ServiceException {
		try {
			breakDao.delete(id);
		} catch(DaoException e) {
			throw new ServiceException(e);
		}
	}
}
