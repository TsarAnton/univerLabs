package dao.pgsql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.RoleDao;
import dao.DaoException;
import domain.Role;

public class RoleDaoSqlImpl implements RoleDao {
	private Connection connection;

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	@Override
	public Long create(Role role) throws DaoException {
		String sql = "INSERT INTO \"role\"(\"role_name\") VALUES (?)";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			statement.setString(1, role.getRole_name());
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
	public Role read(Long id) throws DaoException {
		String sql = "SELECT \"role_name\" FROM \"role\" WHERE \"id\" = ?";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setLong(1, id);
			resultSet = statement.executeQuery();
			Role role = null;
			if(resultSet.next()) {
				role = new Role();
				role.setId(id);
				role.setRole_name(resultSet.getString("role_name"));
			}
			return role;
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { resultSet.close(); } catch (Exception e) {}
			try { statement.close(); } catch (Exception e) {}
		}
	}

	@Override
	public void update(Role role) throws DaoException {
		String sql = "UPDATE \"role\" SET \"role_name\" = ? WHERE \"id\" = ?";
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, role.getRole_name());
			statement.setLong(3, role.getId());
			statement.executeUpdate();
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { statement.close(); } catch (Exception e) {}
		}
	}

	@Override
	public void delete(Long id) throws DaoException {
		String sql = "DELETE FROM \"role\" WHERE \"id\" = ?";
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
	public List<Role> readAll() throws DaoException {
		String sql = "SELECT \"id\", \"role_name\" FROM \"role\"";
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			List<Role> roles = new ArrayList<>();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				Role role = new Role();
				role.setId(resultSet.getLong("id"));
				role.setRole_name(resultSet.getString("role_name"));
				roles.add(role);
			}
			return roles;
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { resultSet.close(); } catch (Exception e) {}
			try { statement.close(); } catch (Exception e) {}
		}
	}

	@Override
	public List<Role> readByUser(Long id) throws DaoException {
		String sql = "SELECT \"role\".\"id\", \"role_name\" FROM (\"role\" INNER JOIN \"user_vs_role\" ON \"role\".\"id\" = \"user_vs_role\".\"role_id\") WHERE \"user_id\" = ?";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			List<Role> roles = new ArrayList<>();
			statement = connection.prepareStatement(sql);
			statement.setLong(1, id);
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				Role role = new Role();
				role.setId(resultSet.getLong("id"));
				role.setRole_name(resultSet.getString("role_name"));
				roles.add(role);
			}
			return roles;
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { resultSet.close(); } catch (Exception e) {}
			try { statement.close(); } catch (Exception e) {}
		}
	}

	@Override
	public List<Role> readWithoutUser(Long id) throws DaoException {
		String sql = "SELECT DISTINCT \"role\".\"id\", \"role_name\" FROM (\"user_vs_role\" INNER JOIN \"role\" ON \"user_vs_role\".\"role_id\" = \"role\".\"id\") WHERE \"role_id\" NOT IN (SELECT \"role_id\" FROM \"user_vs_role\" WHERE \"user_id\" = ? )";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			List<Role> roles = new ArrayList<>();
			statement = connection.prepareStatement(sql);
			statement.setLong(1, id);
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				Role role = new Role();
				role.setId(resultSet.getLong("id"));
				role.setRole_name(resultSet.getString("role_name"));
				roles.add(role);
			}
			return roles;
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { resultSet.close(); } catch (Exception e) {}
			try { statement.close(); } catch (Exception e) {}
		}
	}

	@Override
	public Long createUserRole(Long user_id, Long role_id) throws DaoException {
		String sql = "INSERT INTO \"user_vs_role\" (\"role_id\", \"user_id\") VALUES (?, ?)";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			statement.setLong(1, role_id);
			statement.setLong(2, user_id);
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
	public void deleteUserRole(Long user_id, Long role_id) throws DaoException {
		String sql = "DELETE FROM \"user_vs_role\" WHERE \"user_id\" = ? AND \"role_id\" = ?";
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setLong(1, user_id);
			statement.setLong(2, role_id);
			statement.executeUpdate();
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { statement.close(); } catch (Exception e) {}
		}
	}
}
