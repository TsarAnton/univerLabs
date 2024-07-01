package dao.pgsql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.DriverDao;
import dao.DaoException;
import domain.Driver;

public class DriverDaoSqlImpl implements DriverDao {
	private Connection connection;

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	@Override
	public Long create(Driver driver) throws DaoException {
		return null;
	}

	@Override
	public Driver read(Long id) throws DaoException {
		return null;
	}

	@Override
	public void update(Driver driver) throws DaoException {
	}

	@Override
	public void delete(Long id) throws DaoException {
	}

	@Override
	public List<Driver> readAll() throws DaoException {
		String sql = "SELECT \"id\", \"name\", \"surname\" FROM \"driver\"";
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			List<Driver> drivers = new ArrayList<>();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				Driver driver = new Driver();
				driver.setId(resultSet.getLong("id"));
				driver.setName(resultSet.getString("name"));
				driver.setSurname(resultSet.getString("surname"));
				drivers.add(driver);
			}
			return drivers;
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { resultSet.close(); } catch (Exception e) {}
			try { statement.close(); } catch (Exception e) {}
		}
	}
}
