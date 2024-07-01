package dao;

import java.util.List;

import domain.Request2_1;

public interface Request2_1Dao extends Dao<Request2_1> {
	List<Request2_1> readByDriver(Long id) throws DaoException;
}