package service.request2_1;

import java.util.List;

import domain.Request2_1;

import service.ServiceException;

public interface Request2_1Service {
	List<Request2_1> findByDriver(Long id) throws ServiceException;
}
