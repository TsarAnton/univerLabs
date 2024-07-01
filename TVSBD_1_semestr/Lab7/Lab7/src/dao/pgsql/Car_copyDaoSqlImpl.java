package dao.pgsql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.Car_copyDao;
import dao.DaoException;
import domain.Car_copy;
import domain.Car_copyGetAll;

public class Car_copyDaoSqlImpl implements Car_copyDao {
	private Connection connection;

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	@Override
	public Long create(Car_copy car_copy) throws DaoException {
		String sql = "INSERT INTO \"car_copy\"(\"state_num\", \"car_id\") VALUES (?, ?)";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			statement.setString(1, car_copy.getState_num());
			statement.setLong(2, car_copy.getCar_id());
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
	public Car_copy read(Long id) throws DaoException {
		String sql = "SELECT \"state_num\", \"car_id\" FROM \"car_copy\" WHERE \"id\" = ?";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setLong(1, id);
			resultSet = statement.executeQuery();
			Car_copy car_copy = null;
			if(resultSet.next()) {
				car_copy = new Car_copy();
				car_copy.setId(id);
				car_copy.setState_num(resultSet.getString("state_num"));
				car_copy.setCar_id(resultSet.getLong("car_id"));
			}
			return car_copy;
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { resultSet.close(); } catch (Exception e) {}
			try { statement.close(); } catch (Exception e) {}
		}
	}

	@Override
	public void update(Car_copy car_copy) throws DaoException {
		String sql = "UPDATE \"car_copy\" SET \"state_num\" = ?, \"car_id\" = ? WHERE \"id\" = ?";
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, car_copy.getState_num());
			statement.setLong(2, car_copy.getCar_id());
			statement.setLong(3, car_copy.getId());
			statement.executeUpdate();
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { statement.close(); } catch (Exception e) {}
		}
	}

	@Override
	public void delete(Long id) throws DaoException {
		String sql = "DELETE FROM \"car_copy\" WHERE \"id\" = ?";
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
	public List<Car_copyGetAll> readAll() throws DaoException {
		String sql = "SELECT \"car_copy\".\"id\", \"car_id\", \"mark\", \"model\", \"state_num\" FROM (\"car_copy\" INNER JOIN \"car\" ON \"car_copy\".\"car_id\"=\"car\".\"id\")";
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			List<Car_copyGetAll> car_copys = new ArrayList<>();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				Car_copyGetAll car_copy = new Car_copyGetAll();
				car_copy.setId(resultSet.getLong("id"));
				car_copy.setState_num(resultSet.getString("state_num"));
				car_copy.setCar_id(resultSet.getLong("car_id"));
                car_copy.setMark(resultSet.getString("mark"));
                car_copy.setModel(resultSet.getString("model"));
				car_copys.add(car_copy);
			}
			return car_copys;
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { resultSet.close(); } catch (Exception e) {}
			try { statement.close(); } catch (Exception e) {}
		}
	}
}
