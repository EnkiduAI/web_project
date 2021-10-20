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
import com.epam.web.model.dao.StatusDao;
import com.epam.web.model.entity.Status;


public class StatusDaoImpl implements StatusDao{
	private static final Logger logger = LogManager.getLogger();
	private static StatusDaoImpl instance = new StatusDaoImpl();
	private ConnectionPool connectionPool = ConnectionPool.getInstance();
	
	private StatusDaoImpl() {
		
	}
	
	public static StatusDaoImpl getInstance() {
		return instance;
	}
private static final String SQL_FIND_ALL = """
		select status.statusId, status.status from mydb.status;
		""";
private static final String SQL_FIND_BY_ID = """
		select status.statusId, status.status from mydb.status
		where status.statusId = ?;
		""";
private static final String SQL_DELETE_BY_ID = """
		delete from mydb.status where status.statusId = ?;
		""";
private static final String SQL_INSERT_STATUS = """
		insert into mydb.status(status.statusId, status.status)
		values(NULL, ?);
		""";
private static final String SQL_UPDATE_STATUS = """
		update mydb.status set status.statusId = ?, status.status = ?;
		""";
private static final String SQL_FIND_BY_STATUS = """
		select status.statusId, status.status from mydb.status
		where status.status = ?;
		""";
	@Override
	public List<Status> findAll() throws DaoException {
		List<Status> statusList = new ArrayList<>();
		try (Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL)){
			ResultSet resultSet = statement.executeQuery(SQL_FIND_ALL);
			while(resultSet.next()) {
				Status status = buildStatus(resultSet);
				statusList.add(status);
			}
			}catch (SQLException e) {
				logger.error("Problem at findAll method at StatusDaoImpl", e);
				throw new DaoException("error ocurred at findAll: statusDaoImpl", e);
		}
		return statusList;
	}

	@Override
	public Status findById(Integer id) throws DaoException {
		Status status = new Status();
		try (Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ID)){			
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {	
				status = buildStatus(resultSet);		
			}	
			}catch(SQLException e) {
				logger.error("Problem at findById method at StatusDaoImpl", e);
				throw new DaoException("problem at StatusDao: findById", e);
			}	
		return status;
	}

	@Override
	public boolean delete(Integer id) throws DaoException {
		int result = 0;
		try(Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_DELETE_BY_ID)) {
			statement.setInt(1, id);
			result = statement.executeUpdate();
		}catch(SQLException e) {
			logger.error("Problem at delete(id) method at StatusDaoImpl", e);
			throw new DaoException("error occured during delete by id at StatusDaoImpl", e);
		}
		return result>0;
	}

	@Override
	public boolean delete(Status t) throws DaoException {
		int result = 0;
		try (Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_DELETE_BY_ID)){
			statement.setInt(1, t.getStatusId());
			result = statement.executeUpdate();
		}catch(SQLException e) {
			logger.error("Problem at delete(entity) method at StatusDaoImpl", e);
			throw new DaoException("error ocured during deletion entity at StatusDaoImpl",e);
		}
		return result>0;
	}

	@Override
	public boolean create(Status t) throws DaoException {
		int result = 0;
		try(Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_INSERT_STATUS)) {
			statement.setInt(1, t.getStatusId());
			statement.setString(2, t.getStatus());
			result = statement.executeUpdate();
		}catch(SQLException e) {
			logger.error("Problem at create method at StatusDaoImpl", e);
			throw new DaoException("creation failed at StatusDaoImpl", e);
		}
		return result>0;
	}

	@Override
	public void update(Status t) throws DaoException {
		Status status = new Status();
		status = findById(t.getStatusId());
		try(Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_STATUS)) {
			statement.setInt(1, t.getStatusId());
			statement.setString(2, t.getStatus());
			statement.executeUpdate();
		}catch(SQLException e) {
			logger.error("Problem at update method at StatusDaoImpl", e);
			throw new DaoException("update failed at StatusDaoImpl", e);
		}
		
	}

	@Override
	public Status findByStatus(String status) throws DaoException {
		Status statusMatch = new Status();
		try (Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_STATUS)){			
			statement.setString(1, status);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {	
				statusMatch = buildStatus(resultSet);
			}	
			}catch(SQLException e) {
				logger.error("Problem at findByStatus method at StatusDaoImpl", e);
				throw new DaoException("problem at StatusDao: findByStatus", e);
			}	
		return statusMatch;
	}
	
	private Status buildStatus(ResultSet resultSet) throws SQLException{
		Status statusBuild = new Status.StatusBuilder()
				.setStatusId(resultSet.getInt(STATUS_ID))
				.setStatus(resultSet.getString(STATUS))
				.build();
		return statusBuild;
				
	}

}
