package service.car;

import java.util.List;

import domain.Car;

import service.ServiceException;

public interface CarService {
	List<Car> findAll() throws ServiceException;

	void save(Car user) throws ServiceException;

	void delete(Long id) throws ServiceException;
}
