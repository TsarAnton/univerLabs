package dao.pgsql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.UserDao;
import dao.DaoException;
import domain.User;

public class UserDaoSqlImpl implements UserDao {
	private Connection connection;

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	@Override
	public Long create(User user) throws DaoException {
		String sql = "INSERT INTO \"user\"(\"login\", \"password\") VALUES (?, ?)";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			statement.setString(1, user.getLogin());
			statement.setString(2, user.getPassword());
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
	public User read(Long id) throws DaoException {
		String sql = "SELECT \"login\", \"password\" FROM \"user\" WHERE \"id\" = ?";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setLong(1, id);
			resultSet = statement.executeQuery();
			User user = null;
			if(resultSet.next()) {
				user = new User();
				user.setId(id);
				user.setLogin(resultSet.getString("login"));
				user.setPassword(resultSet.getString("password"));
			}
			return user;
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { resultSet.close(); } catch (Exception e) {}
			try { statement.close(); } catch (Exception e) {}
		}
	}

	@Override
	public void update(User user) throws DaoException {
		String sql = "UPDATE \"user\" SET \"login\" = ?, \"password\" = ? WHERE \"id\" = ?";
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, user.getLogin());
			statement.setString(2, user.getPassword());
			statement.setLong(3, user.getId());
			statement.executeUpdate();
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { statement.close(); } catch (Exception e) {}
		}
	}

	@Override
	public void delete(Long id) throws DaoException {
		String sql = "DELETE FROM \"user\" WHERE \"id\" = ?";
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
	public List<User> readAll() throws DaoException {
		String sql = "SELECT \"id\", \"login\", \"password\" FROM \"user\"";
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			List<User> users = new ArrayList<>();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				User user = new User();
				user.setId(resultSet.getLong("id"));
				user.setLogin(resultSet.getString("login"));
				user.setPassword(resultSet.getString("password"));
				users.add(user);
			}
			return users;
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { resultSet.close(); } catch (Exception e) {}
			try { statement.close(); } catch (Exception e) {}
		}
	}

    @Override
    public List<User> readByRole(Long id) throws DaoException {
        String sql = "SELECT \"user\".\"id\", \"login\", \"password\" FROM (\"user\" INNER JOIN \"user_vs_role\" ON \"user\".\"id\" = \"user_vs_role\".\"user_id\") WHERE \"role_id\" = ?";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			List<User> users = new ArrayList<>();
			statement = connection.prepareStatement(sql);
			statement.setLong(1, id);
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				User user = new User();
				user.setId(resultSet.getLong("id"));
				user.setLogin(resultSet.getString("login"));
				user.setPassword(resultSet.getString("password"));
				users.add(user);
			}
			return users;
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { resultSet.close(); } catch (Exception e) {}
			try { statement.close(); } catch (Exception e) {}
		}
    }

    @Override
    public List<User> readWithoutRole(Long id) throws DaoException {
        String sql = "SELECT DISTINCT \"user\".\"id\", \"login\", \"password\" FROM (\"user_vs_role\" INNER JOIN \"user\" ON \"user_vs_role\".\"user_id\" = \"user\".\"id\") WHERE \"user_id\" NOT IN (SELECT \"user_id\" FROM \"user_vs_role\" WHERE \"role_id\" = ?)";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			List<User> users = new ArrayList<>();
			statement = connection.prepareStatement(sql);
			statement.setLong(1, id);
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				User user = new User();
				user.setId(resultSet.getLong("id"));
				user.setLogin(resultSet.getString("login"));
				user.setPassword(resultSet.getString("password"));
				users.add(user);
			}
			return users;
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { resultSet.close(); } catch (Exception e) {}
			try { statement.close(); } catch (Exception e) {}
		}
    }
}
