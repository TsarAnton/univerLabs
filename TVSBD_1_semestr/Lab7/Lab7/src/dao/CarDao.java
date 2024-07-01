package dao;

import java.util.List;

import domain.Car;

public interface CarDao extends Dao<Car> {
	List<Car> readAll() throws DaoException;
}
