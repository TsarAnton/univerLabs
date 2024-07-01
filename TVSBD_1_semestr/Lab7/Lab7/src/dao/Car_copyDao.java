package dao;

import java.util.List;

import domain.Car_copy;
import domain.Car_copyGetAll;

public interface Car_copyDao extends Dao<Car_copy> {
	List<Car_copyGetAll> readAll() throws DaoException;
}
