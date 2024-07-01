package service.car_copy;

import java.util.List;

import domain.Car_copy;
import domain.Car_copyGetAll;
import service.ServiceException;

public interface Car_copyService {
	List<Car_copyGetAll> findAll() throws ServiceException;

	void save(Car_copy user) throws ServiceException;

	void delete(Long id) throws ServiceException;
}
