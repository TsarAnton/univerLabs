package dao.pgsql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.AuthorDao;
import dao.DaoException;
import domain.Author;

public class AuthorDaoSqlImpl implements AuthorDao {
	private Connection connection;

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	@Override
	public Long create(Author author) throws DaoException {
		String sql = "INSERT INTO \"author\"(\"name\", \"surname\") VALUES (?, ?)";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			statement.setString(1, author.getName());
			statement.setString(2, author.getSurname());
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
	public Author read(Long id) throws DaoException {
		String sql = "SELECT \"name\", \"surname\" FROM \"author\" WHERE \"id\" = ?";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setLong(1, id);
			resultSet = statement.executeQuery();
			Author author = null;
			if(resultSet.next()) {
				author = new Author();
				author.setId(id);
				author.setName(resultSet.getString("name"));
				author.setSurname(resultSet.getString("surname"));
			}
			return author;
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { resultSet.close(); } catch (Exception e) {}
			try { statement.close(); } catch (Exception e) {}
		}
	}

	@Override
	public void update(Author author) throws DaoException {
		String sql = "UPDATE \"author\" SET \"name\" = ?, \"surname\" = ? WHERE \"id\" = ?";
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, author.getName());
			statement.setString(2, author.getSurname());
			statement.setLong(3, author.getId());
			statement.executeUpdate();
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { statement.close(); } catch (Exception e) {}
		}
	}

	@Override
	public void delete(Long id) throws DaoException {
		String sql = "DELETE FROM \"author\" WHERE \"id\" = ?";
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
	public List<Author> readAll() throws DaoException {
		String sql = "SELECT \"id\", \"name\", \"surname\" FROM \"author\"";
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			List<Author> authors = new ArrayList<>();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				Author author = new Author();
				author.setId(resultSet.getLong("id"));
				author.setName(resultSet.getString("name"));
				author.setSurname(resultSet.getString("surname"));
				authors.add(author);
			}
			return authors;
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { resultSet.close(); } catch (Exception e) {}
			try { statement.close(); } catch (Exception e) {}
		}
	}
}
