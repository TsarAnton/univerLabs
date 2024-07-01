package service.car_copy;

import java.util.List;

import dao.Car_copyDao;
import dao.DaoException;
import domain.Car_copy;
import domain.Car_copyGetAll;
import service.ServiceException;

public class Car_copyServiceImpl implements Car_copyService {
	private Car_copyDao car_copyDao;

	public void setCar_copyDao(Car_copyDao car_copyDao) {
		this.car_copyDao = car_copyDao;
	}

	@Override
	public List<Car_copyGetAll> findAll() throws ServiceException {
		try {
			return car_copyDao.readAll();
		} catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void save(Car_copy car_copy) throws ServiceException {
		try {
			if(car_copy.getId() != null) {
				car_copyDao.update(car_copy);
			} else {
				Long id = car_copyDao.create(car_copy);
				car_copy.setId(id);
			}
		} catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void delete(Long id) throws ServiceException {
		try {
			car_copyDao.delete(id);
		} catch(DaoException e) {
			throw new ServiceException(e);
		}
	}
}
