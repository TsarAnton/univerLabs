package service.driver;

import java.util.List;

import dao.DriverDao;
import dao.DaoException;
import domain.Driver;

import service.ServiceException;

public class DriverServiceImpl implements DriverService {
	private DriverDao driverDao;

	public void setDriverDao(DriverDao driverDao) {
		this.driverDao = driverDao;
	}

	@Override
	public List<Driver> findAll() throws ServiceException {
		try {
			return driverDao.readAll();
		} catch(DaoException e) {
			throw new ServiceException(e);
		}
	}
}
