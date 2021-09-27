package com.epam.web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import com.epam.web.connection.ConnectionPool;
import com.epam.web.dao.UserDao;
import com.epam.web.entity.UserEntity;
import com.epam.web.exception.DaoException;
import static com.epam.web.dao.TableColumns.*;

public class UserDaoImpl implements UserDao{
	private static final Logger logger = LogManager.getLogger();
	private static UserDaoImpl instance = new UserDaoImpl();
	private ConnectionPool connectionPool = ConnectionPool.getInstance();
	
	private UserDaoImpl() {
		
	}
	
	public static UserDaoImpl getInstance() {
		return instance;
	}
	
	private static final String SQL_FIND_ALL = """
			select users.userId, users.name, users.surname, users.login, users.password,
			users.email, users.phone 
			from mydb.users;	
			""";
	private static final String SQL_FIND_BY_ID = """
			select users.userId, users.name, users.surname, users.login, users.password,
			users.email, users.phone 
			from mydb.users
			where users.userId = ?;	
			""";
	private static final String SQL_DELETE_BY_ID = """
			delete from mydb.users where users.userId = ?;
			""";
	private static final String SQL_CREATE_USER = """
			insert into mydb.users(users.userId, users.name, users.surname, users.login, users.password,
			users.email, users.phone)
			values(NULL, ?, ?, ?, ?, ?, ?);
			""";
	private static final String SQL_UPDATE_USER = """
			update mydb.users set users.userId = ?, users.name = ?, users.surname = ?, users.login = ?, users.password = ?,
			users.email = ?, users.phone = ?;
			""";
	private static final String SQL_FIND_BY_LOGIN = """
			select users.userId, users.name, users.surname, users.login, users.password,
			users.email, users.phone 
			from mydb.users
			where users.login = ?;	
			""";
	private static final String SQL_FIND_BY_EMAIL = """
			select users.userId, users.name, users.surname, users.login, users.password,
			users.email, users.phone 
			from mydb.users
			where users.email = ?;	
			""";

	@Override
	public List<UserEntity> findAll() throws DaoException {
		List<UserEntity> users = new ArrayList<>();
		try(Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL)) {
			ResultSet resultSet = statement.executeQuery(SQL_FIND_ALL);
			while(resultSet.next()) {
				UserEntity user = buildUser(resultSet);
				users.add(user);
			}
			}catch (SQLException e) {
				logger.error("Problem at findAll method at UserDaoImpl", e);
				throw new DaoException("error ocurred at findAll: UserDaoImpl", e);
		}
		return users;
	}

	@Override
	public UserEntity findById(Integer id) throws DaoException {
		UserEntity user = new UserEntity();
		try(Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ID)) {			
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {	
				user = buildUser(resultSet);
			}	
			}catch(SQLException e) {
				logger.error("Problem at findById method at UserDaoImpl", e);
				throw new DaoException("problem at UserDaoImpl: findById", e);
			}	
		return user;
	}

	@Override
	public boolean delete(Integer id) throws DaoException {
		int result = 0;
		try(Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_DELETE_BY_ID)) {
			statement.setInt(1, id);
			result = statement.executeUpdate();
		}catch(SQLException e) {
			logger.error("Problem at delete(id) method at UserDaoImpl", e);
			throw new DaoException("error occured during delete by id at UserDaoImpl", e);
		}
		return result>0;
	}

	@Override
	public boolean delete(UserEntity t) throws DaoException {
		int result = 0;
		try(Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_DELETE_BY_ID)){
			statement.setInt(1, t.getUserId());
			result = statement.executeUpdate();
		}catch(SQLException e) {
			logger.error("Problem at delete(entity) method at UserDaoImpl", e);
			throw new DaoException("error ocured during deletion entity at UserDaoImpl",e);
		}
		return result>0;
	}

	@Override
	public boolean create(UserEntity t) throws DaoException {
		int result = 0;
		try(Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_CREATE_USER)) {
			statement.setInt(1, t.getUserId());
			statement.setString(2, t.getName());
			statement.setString(3, t.getSurname());
			statement.setString(4, t.getLogin());
			statement.setString(5, t.getPassword());
			statement.setString(6, t.getEmail());
			statement.setString(7, t.getPhone());
			result = statement.executeUpdate();
		}catch(SQLException e) {
			logger.error("Problem at create method at UserDaoImpl", e);
			throw new DaoException("creation failed at UserDaoImpl", e);
		}
		return result>0;
	}

	@Override
	public UserEntity update(UserEntity t) throws DaoException {
		UserEntity user = new UserEntity();
		user = findById(t.getUserId());
		try(Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_USER)) {
			statement.setString(2, t.getName());
			statement.setString(3, t.getSurname());
			statement.setString(4, t.getLogin());
			statement.setString(5, t.getPassword());
			statement.setString(6, t.getEmail());
			statement.setString(7, t.getPhone());
			statement.executeUpdate();
		}catch(SQLException e) {
			logger.error("Problem at update method at UserDaoImpl", e);
			throw new DaoException("update failed at UserDaoImpl", e);
		}
		return user;
	}

	@Override
	public Optional<UserEntity> findByLogin(String login) throws DaoException {
		
		UserEntity user = new UserEntity();
		try (Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_LOGIN)) {			
			statement.setString(1, login);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {	
				user = buildUser(resultSet);
			}	
			}catch(SQLException e) {
				logger.error("Problem at findByLogin method at UserDaoImpl", e);
				throw new DaoException("problem at UserDaoImpl: findById", e);
			}	
		return Optional.of(user);
	}

	@Override
	public UserEntity findByEmail(String email) throws DaoException {
		UserEntity user = new UserEntity();
		try(Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_EMAIL)) {	
			statement.setString(1, email);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {	
				user = buildUser(resultSet);
			}	
			}catch(SQLException e) {
				logger.error("Problem at findByEmail method at UserDaoImpl", e);
				throw new DaoException("problem at UserDaoImpl: findById", e);
			}		
		return user;
	}

	private UserEntity buildUser(ResultSet resultSet) throws SQLException{
		UserEntity user = new UserEntity.UserBuilder()
				.setUserId(resultSet.getInt(USER_ID))
				.setUserName(resultSet.getString(USER_NAME))
				.setSurname(resultSet.getString(USER_SURNAME))
				.setLogin(resultSet.getString(USER_LOGIN))
				.setPassword(resultSet.getString(USER_PASSWORD))
				.setEmail(resultSet.getString(USER_EMAIL))
				.setPhone(resultSet.getString(USER_PHONE))
				.build();
		return user;
	}

}
