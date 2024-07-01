package dao;

import java.util.List;

import domain.Driver;

public interface DriverDao extends Dao<Driver> {
	List<Driver> readAll() throws DaoException;
}
