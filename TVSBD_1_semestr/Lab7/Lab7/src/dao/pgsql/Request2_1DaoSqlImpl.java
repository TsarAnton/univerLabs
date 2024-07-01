package dao.pgsql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.Request2_1Dao;
import dao.DaoException;
import domain.Request2_1;

public class Request2_1DaoSqlImpl implements Request2_1Dao {
	private Connection connection;

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void delete(Long id) throws DaoException {
	}

    @Override
    public Long create(Request2_1 entity) throws DaoException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Request2_1 read(Long id) throws DaoException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void update(Request2_1 entity) throws DaoException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public List<Request2_1> readByDriver(Long id) throws DaoException {
        String sql = "SELECT \"number\" FROM \"telephone\" WHERE \"driver_id\" = ?";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			List<Request2_1> numbers = new ArrayList<>();
			statement = connection.prepareStatement(sql);
			statement.setLong(1, id);
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				Request2_1 number = new Request2_1();
				number.setNumber(resultSet.getString("number"));
				numbers.add(number);
			}
			return numbers;
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { resultSet.close(); } catch (Exception e) {}
			try { statement.close(); } catch (Exception e) {}
		}
    }

	
}

