package com.epam.web.model.dao.impl;

import static com.epam.web.model.dao.TableColumns.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.web.exception.DaoException;
import com.epam.web.model.connection.ConnectionPool;
import com.epam.web.model.dao.UpdateDao;
import com.epam.web.model.entity.Update;

public class UpdateDaoImpl implements UpdateDao{
	private static final Logger logger = LogManager.getLogger();
	private static UpdateDaoImpl instance = new UpdateDaoImpl();
	private ConnectionPool connectionPool = ConnectionPool.getInstance();
	
	private UpdateDaoImpl() {
		
	}
	
	public static UpdateDaoImpl getInstance() {
		return instance;
	}
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
	public List<Update> findAll() throws DaoException {
		List<Update> updates = new ArrayList<>();
		try (Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL)){
			ResultSet resultSet = statement.executeQuery(SQL_FIND_ALL);
			while(resultSet.next()) {
				Update update = buildMessage(resultSet);
				updates.add(update);
			}
			}catch (SQLException e) {
				logger.error("Problem at findAll method at UpdateDaoImpl", e);
				throw new DaoException("error ocurred at findAll: UpdateDaoImpl", e);
		}
		return updates;
	}

	@Override
	public Update findById(Integer id) throws DaoException {
		Update update = new Update();
		try (Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ID)){						
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {	
				update = buildMessage(resultSet);
			}	
			}catch(SQLException e) {
				logger.error("Problem at findById method at UpdateDaoImpl", e);
				throw new DaoException("problem at UpdateDaoImpl: findById", e);
			}		
		return update;
	}

	@Override
	public boolean delete(Integer id) throws DaoException {
		int result = 0;
		try(Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_DELETE_BY_ID)) {
			statement.setInt(1, id);
			result = statement.executeUpdate();
		}catch(SQLException e) {
			logger.error("Problem at delete(id) method at UpdateDaoImpl", e);
			throw new DaoException("error occured during delete by id at UpdateDaoImpl", e);
		}
		return result>0;
	}

	@Override
	public boolean delete(Update t) throws DaoException {
		int result = 0;
		try(Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_DELETE_BY_ID)) {
			statement.setInt(1, t.getMessageId());
			result = statement.executeUpdate();
		}catch(SQLException e) {
			logger.error("Problem at delete(entity) method at UpdateDaoImpl", e);
			throw new DaoException("error ocured during deletion entity at UpdateDaoImpl",e);
		}
		return result>0;
	}

	@Override
	public boolean create(Update t) throws DaoException {
		int result = 0;
		
		try(Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_CREATE_MESSAGE)) {
			statement.setInt(1, t.getMessageId());
			statement.setInt(2, t.getUserId());
			statement.setInt(3, t.getApplicationId());
			statement.setString(4, t.getMessage());
			result = statement.executeUpdate();
		}catch(SQLException e) {
			logger.error("Problem at create method at UpdateDaoImpl", e);
			throw new DaoException("creation failed at UpdateDaoImpl", e);
		}
		return result>0;
	}

	@Override
	public void update(Update t) throws DaoException {
		Update update = new Update();
		update = findById(t.getMessageId());
		try(Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_MESSAGE)) {
			statement.setInt(1, t.getMessageId());
			statement.setInt(2, t.getUserId());
			statement.setInt(3, t.getApplicationId());
			statement.setString(4, t.getMessage());
			statement.executeUpdate();
		}catch(SQLException e) {
			logger.error("Problem at update method at UpdateDaoImpl", e);
			throw new DaoException("update failed at UpdateDaoImpl", e);
		}
		
	}

	@Override
	public List<Update> findByApplicationId(int applicationId) throws DaoException {
		List<Update> updates = new ArrayList<>();
		try (Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_APPLICATION_ID)){			
			statement.setInt(1, applicationId);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {	
				Update update = buildMessage(resultSet);
				updates.add(update);
			}	
			}catch(SQLException e) {
				logger.error("Problem at findByApplicationId method at UpdateDaoImpl", e);
				throw new DaoException("problem at ClosedApplicationDaoImpl: findByStatus", e);
			}		
		return updates;
	}
	
	private Update buildMessage (ResultSet resultSet) throws SQLException{
		Update message = new Update.UpdateBuilder()
				.setMessageId(resultSet.getInt(MESSAGE_ID))
				.setUserId(resultSet.getInt(FK_USER_ID))
				.setApplicationId(resultSet.getInt(UPDATES_APPLICATION_ID))
				.setMessage(resultSet.getString(MESSAGE))
				.build();
		return message;
	}

}
