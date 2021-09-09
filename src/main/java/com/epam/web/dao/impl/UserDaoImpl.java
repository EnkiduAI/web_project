package com.epam.web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.epam.web.connection.ConnectionFactory;
import com.epam.web.dao.UserDao;
import com.epam.web.entity.ClosedApplicationEntity;
import com.epam.web.entity.UserEntity;
import com.epam.web.exception.DaoException;
import static com.epam.web.dao.TableColumns.*;

public class UserDaoImpl implements UserDao{
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
		Connection connection = null;
		Statement statement = null;
		try {
			connection = ConnectionFactory.createConnection();
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(SQL_FIND_ALL);
			while(resultSet.next()) {
				UserEntity user = new UserEntity();
				user.setUserId(resultSet.getInt(USER_ID));
				user.setName(resultSet.getString(USER_NAME));
				user.setSurname(resultSet.getString(USER_SURNAME));
				user.setLogin(resultSet.getString(USER_LOGIN));
				user.setPassword(resultSet.getString(USER_PASSWORD));
				user.setEmail(resultSet.getString(USER_EMAIL));
				user.setPhone(resultSet.getString(USER_PHONE));
				users.add(user);
			}
			}catch (SQLException e) {
				throw new DaoException("error ocurred at findAll: UserDaoImpl", e);
		}finally {
			close(connection);
			close(statement);
		}
		return users;
	}

	@Override
	public UserEntity findById(Integer id) throws DaoException {
		Connection connection = null;
		PreparedStatement statement = null;
		UserEntity user = new UserEntity();
		try {			
			connection = ConnectionFactory.createConnection();
			statement = connection.prepareStatement(SQL_FIND_BY_ID);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {	
				user.setUserId(resultSet.getInt(USER_ID));
				user.setName(resultSet.getString(USER_NAME));
				user.setSurname(resultSet.getString(USER_SURNAME));
				user.setLogin(resultSet.getString(USER_LOGIN));
				user.setPassword(resultSet.getString(USER_PASSWORD));
				user.setEmail(resultSet.getString(USER_EMAIL));
				user.setPhone(resultSet.getString(USER_PHONE));
			}	
			}catch(SQLException e) {
				throw new DaoException("problem at UserDaoImpl: findById", e);
			}finally {
				close(statement);
				close(connection);
			}		
		return user;
	}

	@Override
	public boolean delete(Integer id) throws DaoException {
		int result = 0;
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = ConnectionFactory.createConnection();
			statement = connection.prepareStatement(SQL_DELETE_BY_ID);
			statement.setInt(1, id);
			result = statement.executeUpdate();
		}catch(SQLException e) {
			throw new DaoException("error occured during delete by id at UserDaoImpl", e);
		}finally {
			close(statement);
			close(connection);
		}
		return result>0;
	}

	@Override
	public boolean delete(UserEntity t) throws DaoException {
		int result = 0;
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = ConnectionFactory.createConnection();
			statement = connection.prepareStatement(SQL_DELETE_BY_ID);
			statement.setInt(1, t.getUserId());
			result = statement.executeUpdate();
		}catch(SQLException e) {
			throw new DaoException("error ocured during deletion entity at UserDaoImpl",e);
		}finally {
			close(statement);
			close(connection);
		}
		return result>0;
	}

	@Override
	public boolean create(UserEntity t) throws DaoException {
		int result = 0;
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = ConnectionFactory.createConnection();
			statement = connection.prepareStatement(SQL_CREATE_USER);
			statement.setInt(1, t.getUserId());
			statement.setString(2, t.getName());
			statement.setString(3, t.getSurname());
			statement.setString(4, t.getLogin());
			statement.setString(5, t.getPassword());
			statement.setString(6, t.getEmail());
			statement.setString(7, t.getPhone());
			result = statement.executeUpdate();
		}catch(SQLException e) {
			throw new DaoException("creation failed at UserDaoImpl", e);
		}finally {
			close(statement);
			close(connection);
		}
		return result>0;
	}

	@Override
	public UserEntity update(UserEntity t) throws DaoException {
		UserEntity user = new UserEntity();
		Connection connection = null;
		PreparedStatement statement = null;
		user = findById(t.getUserId());
		try {
			connection = ConnectionFactory.createConnection();
			statement.setInt(1, t.getUserId());
			statement.setString(2, t.getName());
			statement.setString(3, t.getSurname());
			statement.setString(4, t.getLogin());
			statement.setString(5, t.getPassword());
			statement.setString(6, t.getEmail());
			statement.setString(7, t.getPhone());
			statement.executeUpdate();
		}catch(SQLException e) {
			throw new DaoException("update failed at UserDaoImpl", e);
		}finally {
			close(statement);
			close(connection);
		}
		return user;
	}

	@Override
	public UserEntity findByLogin(String login) throws DaoException {
		Connection connection = null;
		PreparedStatement statement = null;
		UserEntity user = new UserEntity();
		try {			
			connection = ConnectionFactory.createConnection();
			statement = connection.prepareStatement(SQL_FIND_BY_ID);
			statement.setString(1, login);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {	
				user.setUserId(resultSet.getInt(USER_ID));
				user.setName(resultSet.getString(USER_NAME));
				user.setSurname(resultSet.getString(USER_SURNAME));
				user.setLogin(resultSet.getString(USER_LOGIN));
				user.setPassword(resultSet.getString(USER_PASSWORD));
				user.setEmail(resultSet.getString(USER_EMAIL));
				user.setPhone(resultSet.getString(USER_PHONE));
			}	
			}catch(SQLException e) {
				throw new DaoException("problem at UserDaoImpl: findById", e);
			}finally {
				close(statement);
				close(connection);
			}		
		return user;
	}

	@Override
	public UserEntity findByEmail(String email) throws DaoException {
		Connection connection = null;
		PreparedStatement statement = null;
		UserEntity user = new UserEntity();
		try {			
			connection = ConnectionFactory.createConnection();
			statement = connection.prepareStatement(SQL_FIND_BY_ID);
			statement.setString(1, email);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {	
				user.setUserId(resultSet.getInt(USER_ID));
				user.setName(resultSet.getString(USER_NAME));
				user.setSurname(resultSet.getString(USER_SURNAME));
				user.setLogin(resultSet.getString(USER_LOGIN));
				user.setPassword(resultSet.getString(USER_PASSWORD));
				user.setEmail(resultSet.getString(USER_EMAIL));
				user.setPhone(resultSet.getString(USER_PHONE));
			}	
			}catch(SQLException e) {
				throw new DaoException("problem at UserDaoImpl: findById", e);
			}finally {
				close(statement);
				close(connection);
			}		
		return user;
	}

}
