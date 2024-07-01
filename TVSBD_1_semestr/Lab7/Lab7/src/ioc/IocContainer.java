package ioc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import dao.BreakDao;
import dao.CarDao;
import dao.Car_copyDao;
import dao.DriverDao;
import dao.Request2_1Dao;
import dao.RoleDao;
import dao.UserDao;
import dao.pgsql.BreakDaoSqlImpl;
import dao.pgsql.CarDaoSqlImpl;
import dao.pgsql.Car_copyDaoSqlImpl;
import dao.pgsql.DriverDaoSqlImpl;
import dao.pgsql.Request2_1DaoSqlImpl;
import dao.pgsql.RoleDaoSqlImpl;
import dao.pgsql.UserDaoSqlImpl;
import service.break1.BreakService;
import service.break1.BreakServiceImpl;
import service.car.CarService;
import service.car.CarServiceImpl;
import service.car_copy.Car_copyService;
import service.car_copy.Car_copyServiceImpl;
import service.driver.DriverService;
import service.driver.DriverServiceImpl;
import service.request2_1.Request2_1Service;
import service.request2_1.Request2_1ServiceImpl;
import service.role.RoleService;
import service.role.RoleServiceImpl;
import service.user.UserService;
import service.user.UserServiceImpl;

public class IocContainer implements AutoCloseable {
	public BreakService getBreakService() throws ContainerException {
		BreakServiceImpl service = new BreakServiceImpl();
		service.setBreakDao(getBreakDao());
		return service;
	}

	public BreakDao getBreakDao() throws ContainerException {
		BreakDaoSqlImpl dao = new BreakDaoSqlImpl();
		dao.setConnection(getConnection());
		return dao;
	}

	public UserService getUserService() throws ContainerException {
		UserServiceImpl service = new UserServiceImpl();
		service.setUserDao(getUserDao());
		return service;
	}

	public UserDao getUserDao() throws ContainerException {
		UserDaoSqlImpl dao = new UserDaoSqlImpl();
		dao.setConnection(getConnection());
		return dao;
	}

	public RoleService getRoleService() throws ContainerException {
		RoleServiceImpl service = new RoleServiceImpl();
		service.setRoleDao(getRoleDao());
		return service;
	}

	public RoleDao getRoleDao() throws ContainerException {
		RoleDaoSqlImpl dao = new RoleDaoSqlImpl();
		dao.setConnection(getConnection());
		return dao;
	}

	public CarService getCarService() throws ContainerException {
		CarServiceImpl service = new CarServiceImpl();
		service.setCarDao(getCarDao());
		return service;
	}

	public CarDao getCarDao() throws ContainerException {
		CarDaoSqlImpl dao = new CarDaoSqlImpl();
		dao.setConnection(getConnection());
		return dao;
	}

	public Car_copyService getCar_copyService() throws ContainerException {
		Car_copyServiceImpl service = new Car_copyServiceImpl();
		service.setCar_copyDao(getCar_copyDao());
		return service;
	}

	public Car_copyDao getCar_copyDao() throws ContainerException {
		Car_copyDaoSqlImpl dao = new Car_copyDaoSqlImpl();
		dao.setConnection(getConnection());
		return dao;
	}

	public DriverService getDriverService() throws ContainerException {
		DriverServiceImpl service = new DriverServiceImpl();
		service.setDriverDao(getDriverDao());
		return service;
	}

	public DriverDao getDriverDao() throws ContainerException {
		DriverDaoSqlImpl dao = new DriverDaoSqlImpl();
		dao.setConnection(getConnection());
		return dao;
	}

	public Request2_1Service getRequest2_1Service() throws ContainerException {
		Request2_1ServiceImpl service = new Request2_1ServiceImpl();
		service.setRequest2_1Dao(getRequest2_1Dao());
		return service;
	}

	public Request2_1Dao getRequest2_1Dao() throws ContainerException {
		Request2_1DaoSqlImpl dao = new Request2_1DaoSqlImpl();
		dao.setConnection(getConnection());
		return dao;
	}

	private Connection connection;
	public Connection getConnection() throws ContainerException {
		if(connection == null) {
			try {
				connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/trip_db", "postgres", "2003");
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
