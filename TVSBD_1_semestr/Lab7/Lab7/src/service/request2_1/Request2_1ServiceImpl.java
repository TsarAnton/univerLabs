package service.request2_1;

import java.util.List;

import dao.Request2_1Dao;
import dao.DaoException;
import domain.Request2_1;

import service.ServiceException;

public class Request2_1ServiceImpl implements Request2_1Service {
	private Request2_1Dao request2_1Dao;

	public void setRequest2_1Dao(Request2_1Dao request2_1Dao) {
		this.request2_1Dao = request2_1Dao;
	}

	@Override
	public List<Request2_1> findByDriver(Long id) throws ServiceException {
		try {
			return request2_1Dao.readByDriver(id);
		} catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

}
