package com.epam.web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.epam.web.connection.ConnectionFactory;
import com.epam.web.dao.StatusDao;
import com.epam.web.dao.TableColumns;
import com.epam.web.entity.StatusEntity;
import com.epam.web.exception.DaoException;

public class StatusDaoImpl implements StatusDao{
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
	public List<StatusEntity> findAll() throws DaoException {
		List<StatusEntity> statusList = new ArrayList<>();
		Connection connection = null;
		Statement statement = null;
		try {
			connection = ConnectionFactory.createConnection();
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(SQL_FIND_ALL);
			while(resultSet.next()) {
				StatusEntity status = new StatusEntity();
				status.setStatusId(resultSet.getInt(TableColumns.STATUS_ID));
				status.setStatus(resultSet.getString(TableColumns.STATUS));
				statusList.add(status);
			}
			}catch (SQLException e) {
				throw new DaoException("error ocurred at findAll: statusDaoImpl", e);
		}finally {
			close(connection);
			close(statement);
		}
		return statusList;
	}

	@Override
	public StatusEntity findById(Integer id) throws DaoException {
		Connection connection = null;
		PreparedStatement statement = null;
		StatusEntity status = new StatusEntity();
		try {			
			connection = ConnectionFactory.createConnection();
			statement = connection.prepareStatement(SQL_FIND_BY_ID);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {	
				status.setStatusId(resultSet.getInt(TableColumns.STATUS_ID));
				status.setStatus(resultSet.getString(TableColumns.STATUS));		
			}	
			}catch(SQLException e) {
				throw new DaoException("problem at StatusDao: findById", e);
			}finally {
				close(statement);
				close(connection);
			}		
		return status;
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
			throw new DaoException("error occured during delete by id at StatusDaoImpl", e);
		}finally {
			close(statement);
			close(connection);
		}
		return result>0;
	}

	@Override
	public boolean delete(StatusEntity t) throws DaoException {
		int result = 0;
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = ConnectionFactory.createConnection();
			statement = connection.prepareStatement(SQL_DELETE_BY_ID);
			statement.setInt(1, t.getStatusId());
			result = statement.executeUpdate();
		}catch(SQLException e) {
			throw new DaoException("error ocured during deletion entity at StatusDaoImpl",e);
		}finally {
			close(statement);
			close(connection);
		}
		return result>0;
	}

	@Override
	public boolean create(StatusEntity t) throws DaoException {
		int result = 0;
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = ConnectionFactory.createConnection();
			statement = connection.prepareStatement(SQL_INSERT_STATUS);
			statement.setInt(1, t.getStatusId());
			statement.setString(2, t.getStatus());
			result = statement.executeUpdate();
		}catch(SQLException e) {
			throw new DaoException("creation failed at StatusDaoImpl", e);
		}finally {
			close(statement);
			close(connection);
		}
		return result>0;
	}

	@Override
	public StatusEntity update(StatusEntity t) throws DaoException {
		StatusEntity status = new StatusEntity();
		Connection connection = null;
		PreparedStatement statement = null;
		status = findById(t.getStatusId());
		try {
			connection = ConnectionFactory.createConnection();
			statement = connection.prepareStatement(SQL_UPDATE_STATUS);
			statement.setInt(1, t.getStatusId());
			statement.setString(2, t.getStatus());
			statement.executeUpdate();
		}catch(SQLException e) {
			throw new DaoException("update failed at StatusDaoImpl", e);
		}finally {
			close(statement);
			close(connection);
		}
		return status;
	}

	@Override
	public List<StatusEntity> findByStatus(String status) throws DaoException {
		Connection connection = null;
		PreparedStatement statement = null;
		List<StatusEntity> statusList = new ArrayList<>();
		try {			
			connection = ConnectionFactory.createConnection();
			statement = connection.prepareStatement(SQL_FIND_BY_STATUS);
			statement.setString(1, status);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {	
				StatusEntity statusMatch = new StatusEntity();
				statusMatch.setStatusId(resultSet.getInt(TableColumns.STATUS_ID));
				statusMatch.setStatus(resultSet.getString(TableColumns.STATUS));		
			}	
			}catch(SQLException e) {
				throw new DaoException("problem at StatusDao: findByStatus", e);
			}finally {
				close(statement);
				close(connection);
			}		
		return statusList;
	}

}
