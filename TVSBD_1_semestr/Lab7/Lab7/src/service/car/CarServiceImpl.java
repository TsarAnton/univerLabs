package service.car;

import java.util.List;

import dao.CarDao;
import dao.DaoException;
import domain.Car;

import service.ServiceException;

public class CarServiceImpl implements CarService {
	private CarDao carDao;

	public void setCarDao(CarDao carDao) {
		this.carDao = carDao;
	}

	@Override
	public List<Car> findAll() throws ServiceException {
		try {
			return carDao.readAll();
		} catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void save(Car car) throws ServiceException {
		try {
			if(car.getId() != null) {
				carDao.update(car);
			} else {
				Long id = carDao.create(car);
				car.setId(id);
			}
		} catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void delete(Long id) throws ServiceException {
		try {
			carDao.delete(id);
		} catch(DaoException e) {
			throw new ServiceException(e);
		}
	}
}
