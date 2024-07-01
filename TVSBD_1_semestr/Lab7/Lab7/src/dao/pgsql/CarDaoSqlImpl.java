package dao.pgsql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.CarDao;
import dao.DaoException;
import domain.Car;

public class CarDaoSqlImpl implements CarDao {
	private Connection connection;

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	@Override
	public Long create(Car car) throws DaoException {
		String sql = "INSERT INTO \"car\"(\"mark\", \"model\", \"tonnage\") VALUES (?, ?, 1)";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			statement.setString(1, car.getMark());
			statement.setString(2, car.getModel());
			statement.executeUpdate();
			resultSet = statement.getGeneratedKeys();
			resultSet.next();
			return resultSet.getLong(1);
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { resultSet.close(); } catch (Exception e) {}
			try { statement.close(); } catch (Exception e) {}
		}
	}

	@Override
	public Car read(Long id) throws DaoException {
		String sql = "SELECT \"mark\", \"model\" FROM \"car\" WHERE \"id\" = ?";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setLong(1, id);
			resultSet = statement.executeQuery();
			Car car = null;
			if(resultSet.next()) {
				car = new Car();
				car.setId(id);
				car.setMark(resultSet.getString("mark"));
				car.setModel(resultSet.getString("model"));
			}
			return car;
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { resultSet.close(); } catch (Exception e) {}
			try { statement.close(); } catch (Exception e) {}
		}
	}

	@Override
	public void update(Car car) throws DaoException {
		String sql = "UPDATE \"car\" SET \"mark\" = ?, \"model\" = ? WHERE \"id\" = ?";
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, car.getMark());
			statement.setString(2, car.getModel());
			statement.setLong(3, car.getId());
			statement.executeUpdate();
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { statement.close(); } catch (Exception e) {}
		}
	}

	@Override
	public void delete(Long id) throws DaoException {
		String sql = "DELETE FROM \"car\" WHERE \"id\" = ?";
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setLong(1, id);
			statement.executeUpdate();
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { statement.close(); } catch (Exception e) {}
		}
	}

	@Override
	public List<Car> readAll() throws DaoException {
		String sql = "SELECT \"id\", \"mark\", \"model\" FROM \"car\"";
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			List<Car> cars = new ArrayList<>();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				Car car = new Car();
				car.setId(resultSet.getLong("id"));
				car.setMark(resultSet.getString("mark"));
				car.setModel(resultSet.getString("model"));
				cars.add(car);
			}
			return cars;
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { resultSet.close(); } catch (Exception e) {}
			try { statement.close(); } catch (Exception e) {}
		}
	}
}
