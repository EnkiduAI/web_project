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


// TODO: Auto-generated Javadoc
/**
 * The Class StatusDaoImpl.
 */
public class StatusDaoImpl implements StatusDao{
	
	/** The Constant logger. */
	private static final Logger logger = LogManager.getLogger();
	
	/** The instance. */
	private static StatusDaoImpl instance = new StatusDaoImpl();
	
	/** The connection pool. */
	private ConnectionPool connectionPool = ConnectionPool.getInstance();
	
	/**
	 * Instantiates a new status dao impl.
	 */
	private StatusDaoImpl() {
		
	}
	
	/**
	 * Gets the single instance of StatusDaoImpl.
	 *
	 * @return single instance of StatusDaoImpl
	 */
	public static StatusDaoImpl getInstance() {
		return instance;
	}

/** The Constant SQL_FIND_ALL. */
private static final String SQL_FIND_ALL = """
		select status.statusId, status.status from mydb.status;
		""";

/** The Constant SQL_FIND_BY_ID. */
private static final String SQL_FIND_BY_ID = """
		select status.statusId, status.status from mydb.status
		where status.statusId = ?;
		""";

/** The Constant SQL_DELETE_BY_ID. */
private static final String SQL_DELETE_BY_ID = """
		delete from mydb.status where status.statusId = ?;
		""";

/** The Constant SQL_INSERT_STATUS. */
private static final String SQL_INSERT_STATUS = """
		insert into mydb.status(status.statusId, status.status)
		values(NULL, ?);
		""";

/** The Constant SQL_UPDATE_STATUS. */
private static final String SQL_UPDATE_STATUS = """
		update mydb.status set status.statusId = ?, status.status = ?;
		""";

/** The Constant SQL_FIND_BY_STATUS. */
private static final String SQL_FIND_BY_STATUS = """
		select status.statusId, status.status from mydb.status
		where status.status = ?;
		""";
	
	/**
	 * Find all.
	 *
	 * @return the list
	 * @throws DaoException the dao exception
	 */
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

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the status
	 * @throws DaoException the dao exception
	 */
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

	/**
	 * Delete by id.
	 *
	 * @param id the id
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
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

	/**
	 * Delete object.
	 *
	 * @param t the t
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	@Override
	public boolean delete(Status status) throws DaoException {
		int result = 0;
		try (Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_DELETE_BY_ID)){
			statement.setInt(1, status.getStatusId());
			result = statement.executeUpdate();
		}catch(SQLException e) {
			logger.error("Problem at delete(entity) method at StatusDaoImpl", e);
			throw new DaoException("error ocured during deletion entity at StatusDaoImpl",e);
		}
		return result>0;
	}

	/**
	 * Creates the.
	 *
	 * @param t the t
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	@Override
	public boolean create(Status status) throws DaoException {
		int result = 0;
		try(Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_INSERT_STATUS)) {
			statement.setInt(1, status.getStatusId());
			statement.setString(2, status.getStatus());
			result = statement.executeUpdate();
		}catch(SQLException e) {
			logger.error("Problem at create method at StatusDaoImpl", e);
			throw new DaoException("creation failed at StatusDaoImpl", e);
		}
		return result>0;
	}

	/**
	 * Update.
	 *
	 * @param t the t
	 * @throws DaoException the dao exception
	 */
	@Override
	public void update(Status status) throws DaoException {
		status = findById(status.getStatusId());
		try(Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_STATUS)) {
			statement.setInt(1, status.getStatusId());
			statement.setString(2, status.getStatus());
			statement.executeUpdate();
		}catch(SQLException e) {
			logger.error("Problem at update method at StatusDaoImpl", e);
			throw new DaoException("update failed at StatusDaoImpl", e);
		}
		
	}

	/**
	 * Find by status.
	 *
	 * @param status the status
	 * @return the status
	 * @throws DaoException the dao exception
	 */
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
	
	/**
	 * Builds the status.
	 *
	 * @param resultSet the result set
	 * @return the status
	 * @throws SQLException the SQL exception
	 */
	private Status buildStatus(ResultSet resultSet) throws SQLException{
		return new Status.StatusBuilder()
				.setStatusId(resultSet.getInt(STATUS_ID))
				.setStatus(resultSet.getString(STATUS))
				.build();
				
	}

}
