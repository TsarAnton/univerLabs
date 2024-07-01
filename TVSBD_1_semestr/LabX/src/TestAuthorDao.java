import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import dao.DaoException;
import dao.pgsql.AuthorDaoSqlImpl;
import domain.Author;

public class TestAuthorDao {
	public static void main(String[] args) {
		Connection connection = null;
		try {
			Class.forName("org.postgresql.Driver");
			System.out.println("Loading driver OK");
			connection = DriverManager.getConnection("jdbc:postgresql://localhost/lib_db", "root", "root");
			System.out.println("Connection to database OK");

			AuthorDaoSqlImpl dao = new AuthorDaoSqlImpl();
			dao.setConnection(connection);

			List<Author> authors = dao.readAll();
			for(Author author : authors) {
				System.out.printf(
					"ID = %d, NAME = %s, SURNAME = %s\n",
					author.getId(),
					author.getName(),
					author.getSurname()
				);
			}

		} catch(SQLException | ClassNotFoundException | DaoException e) {
			System.out.println("Error. " + e.getMessage());
		} finally {
			try { connection.close(); System.out.println("Connection close OK"); } catch (Exception e) {}
		}
	}
}
