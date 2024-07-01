package ioc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import dao.AuthorDao;
import dao.pgsql.AuthorDaoSqlImpl;
import service.AuthorService;
import service.AuthorServiceImpl;

public class IocContainer implements AutoCloseable {
	public AuthorService getAuthorService() throws ContainerException {
		AuthorServiceImpl service = new AuthorServiceImpl();
		service.setAuthorDao(getAuthorDao());
		return service;
	}

	public AuthorDao getAuthorDao() throws ContainerException {
		AuthorDaoSqlImpl dao = new AuthorDaoSqlImpl();
		dao.setConnection(getConnection());
		return dao;
	}

	private Connection connection;
	public Connection getConnection() throws ContainerException {
		if(connection == null) {
			try {
				connection = DriverManager.getConnection("jdbc:postgresql://localhost/lib_db", "root", "root");
			} catch(SQLException e) {
				throw new ContainerException(e);
			}
		}
		return connection;
	}

	@Override
	public void close() {
		try { connection.close(); } catch (Exception e) {}
	}
}
