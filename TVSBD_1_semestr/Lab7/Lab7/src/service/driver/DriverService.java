package service.driver;

import java.util.List;

import domain.Driver;

import service.ServiceException;

public interface DriverService {
	List<Driver> findAll() throws ServiceException;
}
