package service.break1;

import java.util.List;

import domain.Break;

import service.ServiceException;

public interface BreakService {
	List<Break> findAll() throws ServiceException;

	void save(Break break1) throws ServiceException;

	void delete(Long id) throws ServiceException;
}
