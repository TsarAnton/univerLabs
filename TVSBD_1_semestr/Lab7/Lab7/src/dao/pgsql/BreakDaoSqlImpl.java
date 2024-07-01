package dao.pgsql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.BreakDao;
import dao.DaoException;
import domain.Break;

public class BreakDaoSqlImpl implements BreakDao {
	private Connection connection;

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	@Override
	public Long create(Break break1) throws DaoException {
		String sql = "INSERT INTO \"break\"(\"break_notes\", \"repair_notes\") VALUES (?, ?)";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			statement.setString(1, break1.getBreak_notes());
			statement.setString(2, break1.getRepair_notes());
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
	public Break read(Long id) throws DaoException {
		String sql = "SELECT \"break_notes\", \"repair_notes\" FROM \"break\" WHERE \"id\" = ?";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setLong(1, id);
			resultSet = statement.executeQuery();
			Break break1 = null;
			if(resultSet.next()) {
				break1 = new Break();
				break1.setId(id);
				break1.setBreak_notes(resultSet.getString("break_notes"));
				break1.setRepair_notes(resultSet.getString("repair_notes"));
			}
			return break1;
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { resultSet.close(); } catch (Exception e) {}
			try { statement.close(); } catch (Exception e) {}
		}
	}

	@Override
	public void update(Break break1) throws DaoException {
		String sql = "UPDATE \"break\" SET \"break_notes\" = ?, \"repair_notes\" = ? WHERE \"id\" = ?";
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, break1.getBreak_notes());
			statement.setString(2, break1.getRepair_notes());
			statement.setLong(3, break1.getId());
			statement.executeUpdate();
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { statement.close(); } catch (Exception e) {}
		}
	}

	@Override
	public void delete(Long id) throws DaoException {
		String sql = "DELETE FROM \"break\" WHERE \"id\" = ?";
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
	public List<Break> readAll() throws DaoException {
		String sql = "SELECT \"id\", \"break_notes\", \"repair_notes\" FROM \"break\"";
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			List<Break> breaks = new ArrayList<>();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				Break break1 = new Break();
				break1.setId(resultSet.getLong("id"));
				break1.setBreak_notes(resultSet.getString("break_notes"));
				break1.setRepair_notes(resultSet.getString("repair_notes"));
				breaks.add(break1);
			}
			return breaks;
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { resultSet.close(); } catch (Exception e) {}
			try { statement.close(); } catch (Exception e) {}
		}
	}
}
