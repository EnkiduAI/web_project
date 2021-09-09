package com.epam.web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.epam.web.connection.ConnectionFactory;
import com.epam.web.dao.UpdateDao;
import com.epam.web.entity.UpdateEntity;
import com.epam.web.exception.DaoException;
import static com.epam.web.dao.TableColumns.*;

public class UpdateDaoImpl implements UpdateDao{
	private static final String SQL_FIND_ALL = """
			select updates.messageId, updates.userId,
			updates.applicationId, updates.message 
			from mydb.updates;	
			""";
	private static final String SQL_FIND_BY_ID = """
			select updates.messageId, updates.userId,
			updates.applicationId, updates.message 
			from mydb.updates
			where updates.messageId = ?;	
			""";
	private static final String SQL_DELETE_BY_ID = """
			delete from mydb.updates where updates.messageId = ?;
			""";
	private static final String SQL_CREATE_MESSAGE = """
			insert into mydb.updates(updates.messageId, updates.userId,
			updates.applicationId, updates.message)
			values(NULL, ?, ?, ?);
			""";
	private static final String SQL_UPDATE_MESSAGE = """
			update mydb.updates set updates.messageId = ?, updates.userId = ?,
			updates.applicationId = ?, updates.message = ?;
			""";
	private static final String SQL_FIND_BY_APPLICATION_ID = """
			select updates.messageId, updates.userId,
			updates.applicationId, updates.message 
			from mydb.updates
			where updates.applicationId.applicantId = ?;
			""";

	@Override
	public List<UpdateEntity> findAll() throws DaoException {
		List<UpdateEntity> updates = new ArrayList<>();
		Connection connection = null;
		Statement statement = null;
		try {
			connection = ConnectionFactory.createConnection();
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(SQL_FIND_ALL);
			while(resultSet.next()) {
				UpdateEntity update = new UpdateEntity();
				update.setMessageId(resultSet.getInt(MESSAGE_ID));
				update.setUserId(resultSet.getInt(FK_USER_ID));
				update.setApplicationId(resultSet.getInt(UPDATES_APPLICATION_ID));
				update.setMessage(resultSet.getString(MESSAGE));
				updates.add(update);
			}
			}catch (SQLException e) {
				throw new DaoException("error ocurred at findAll: UpdateDaoImpl", e);
		}finally {
			close(connection);
			close(statement);
		}
		return updates;
	}

	@Override
	public UpdateEntity findById(Integer id) throws DaoException {
		Connection connection = null;
		PreparedStatement statement = null;
		UpdateEntity update = new UpdateEntity();
		try {			
			connection = ConnectionFactory.createConnection();
			statement = connection.prepareStatement(SQL_FIND_BY_ID);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {	
				update.setMessageId(resultSet.getInt(MESSAGE_ID));
				update.setUserId(resultSet.getInt(FK_USER_ID));
				update.setApplicationId(resultSet.getInt(UPDATES_APPLICATION_ID));
				update.setMessage(resultSet.getString(MESSAGE));
			}	
			}catch(SQLException e) {
				throw new DaoException("problem at UpdateDaoImpl: findById", e);
			}finally {
				close(statement);
				close(connection);
			}		
		return update;
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
			throw new DaoException("error occured during delete by id at UpdateDaoImpl", e);
		}finally {
			close(statement);
			close(connection);
		}
		return result>0;
	}

	@Override
	public boolean delete(UpdateEntity t) throws DaoException {
		int result = 0;
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = ConnectionFactory.createConnection();
			statement = connection.prepareStatement(SQL_DELETE_BY_ID);
			statement.setInt(1, t.getMessageId());
			result = statement.executeUpdate();
		}catch(SQLException e) {
			throw new DaoException("error ocured during deletion entity at UpdateDaoImpl",e);
		}finally {
			close(statement);
			close(connection);
		}
		return result>0;
	}

	@Override
	public boolean create(UpdateEntity t) throws DaoException {
		int result = 0;
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = ConnectionFactory.createConnection();
			statement = connection.prepareStatement(SQL_CREATE_MESSAGE);
			statement.setInt(1, t.getMessageId());
			statement.setInt(2, t.getUserId());
			statement.setInt(3, t.getApplicationId());
			statement.setString(4, t.getMessage());
			result = statement.executeUpdate();
		}catch(SQLException e) {
			throw new DaoException("creation failed at UpdateDaoImpl", e);
		}finally {
			close(statement);
			close(connection);
		}
		return result>0;
	}

	@Override
	public UpdateEntity update(UpdateEntity t) throws DaoException {
		UpdateEntity update = new UpdateEntity();
		Connection connection = null;
		PreparedStatement statement = null;
		update = findById(t.getMessageId());
		try {
			connection = ConnectionFactory.createConnection();
			statement = connection.prepareStatement(SQL_UPDATE_MESSAGE);
			statement.setInt(1, t.getMessageId());
			statement.setInt(2, t.getUserId());
			statement.setInt(3, t.getApplicationId());
			statement.setString(4, t.getMessage());
			statement.executeUpdate();
		}catch(SQLException e) {
			throw new DaoException("update failed at UpdateDaoImpl", e);
		}finally {
			close(statement);
			close(connection);
		}
		return update;
	}

	@Override
	public List<UpdateEntity> findByApplicationId(int applicationId) throws DaoException {
		Connection connection = null;
		PreparedStatement statement = null;
		List<UpdateEntity> updates = new ArrayList<>();
		try {			
			connection = ConnectionFactory.createConnection();
			statement = connection.prepareStatement(SQL_FIND_BY_APPLICATION_ID);
			statement.setInt(1, applicationId);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {	
				UpdateEntity update = new UpdateEntity();
				update.setMessageId(resultSet.getInt(MESSAGE_ID));
				update.setUserId(resultSet.getInt(FK_USER_ID));
				update.setApplicationId(resultSet.getInt(UPDATES_APPLICATION_ID));
				update.setMessage(resultSet.getString(MESSAGE));
				updates.add(update);
			}	
			}catch(SQLException e) {
				throw new DaoException("problem at ClosedApplicationDaoImpl: findByStatus", e);
			}finally {
				close(statement);
				close(connection);
			}		
		return updates;
	}

}
