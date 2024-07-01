package dao;

import java.util.List;

import domain.Break;

public interface BreakDao extends Dao<Break> {
	List<Break> readAll() throws DaoException;
}
