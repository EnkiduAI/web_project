package com.epam.web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import com.epam.web.connection.ConnectionPool;
import com.epam.web.dao.ClosedApplicationsDao;
import com.epam.web.entity.ClosedApplicationEntity;
import com.epam.web.exception.DaoException;
import static com.epam.web.dao.TableColumns.*;

public class ClosedApplicationDaoImpl implements ClosedApplicationsDao{
	private static final Logger logger = LogManager.getLogger();
	private static ClosedApplicationDaoImpl instance = new ClosedApplicationDaoImpl();
	private ConnectionPool connectionPool = ConnectionPool.getInstance();
	
	private ClosedApplicationDaoImpl() {
		
	}
	
	public static ClosedApplicationDaoImpl getInstance() {
		return instance;
	}
	
private static final String SQL_FIND_ALL = """
		select closed_applications.closedApplicationId, closed_applications.applicationId
		closed_applications.applicantId, closed_applications.date 
		from mydb.closed_applications;	
		""";

private static final String SQL_FIND_BY_ID = """
		select closed_applications.closedApplicationId, closed_applications.applicationId
		closed_applications.applicantId, closed_applications.date 
		from mydb.closed_applications
		where closed_applications.closedApplicationId = ?;
		""";

private static final String SQL_DELETE_BY_ID = """
		delete from mydb.closed_applications where closed_applications.closedApplicationId = ?;
		""";

private static final String SQL_CREATE_CLOSED_APPLICATION = """
insert into mydb.closed_applications(closed_applications.closedApplicationId, closed_applications.applicationId,
		closed_applications.applicantId, closed_applications.date)
values(NULL, ?, ?, ?);
""";

private static final String SQL_UPDATE_STATUS = """
update mydb.closed_applications set closed_applications.closedApplicationId = ?, closed_applications.applicationId = ?
		closed_applications.applicantId = ?, closed_applications.date = ?;
""";

private static final String SQL_FIND_BY_APPLICANT_ID = """
		select closed_applications.closedApplicationId, closed_applications.applicationId
		closed_applications.applicantId, closed_applications.date 
		from mydb.closed_applications
		where closed_applications.applicantId = ?;
		""";

	@Override
	public List<ClosedApplicationEntity> findAll() throws DaoException {
		List<ClosedApplicationEntity> closedApplicationsList = new ArrayList<>();
		try(Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL)) {
			ResultSet resultSet = statement.executeQuery(SQL_FIND_ALL);
			while(resultSet.next()) {
				ClosedApplicationEntity closedApplication = buildClosedApplication(resultSet);
				closedApplicationsList.add(closedApplication);
			}
			}catch (SQLException e) {
				logger.error("Problem at findAll method at ClosedApplicationDaoImpl", e);
				throw new DaoException("error ocurred at findAll: ClosedApplicationDaoImpl", e);
		}
		return closedApplicationsList;
	}

	@Override
	public ClosedApplicationEntity findById(Integer id) throws DaoException {
		ClosedApplicationEntity closedApplication = new ClosedApplicationEntity();
		try(Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ID)) {			
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {	
				closedApplication = buildClosedApplication(resultSet);
				closedApplication.setDate(resultSet.getDate(DATE));
			}	
			}catch(SQLException e) {
				logger.error("Problem at findById method at ClosedApplicationDaoImpl", e);
				throw new DaoException("problem at ClosedApplicationDaoImpl: findById", e);
			}	
		return closedApplication;
	}

	@Override
	public boolean delete(Integer id) throws DaoException {
		int result = 0;
		try(Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_DELETE_BY_ID)) {
			statement.setInt(1, id);
			result = statement.executeUpdate();
		}catch(SQLException e) {
			logger.error("Problem at delete(Id) method at ClosedApplicationDaoImpl", e);
			throw new DaoException("error occured during delete by id at ClosedApplicationDaoImpl", e);
		}
		return result>0;
	}

	@Override
	public boolean delete(ClosedApplicationEntity t) throws DaoException {
		int result = 0;
		try (Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_DELETE_BY_ID)){
			statement.setInt(1, t.getClosedApplicationId());
			result = statement.executeUpdate();
		}catch(SQLException e) {
			logger.error("Problem at delete(entity) method at ClosedApplicationDaoImpl", e);
			throw new DaoException("error ocured during deletion entity at ClosedApplicationDaoImpl",e);
		}
		return result>0;
	}

	@Override
	public boolean create(ClosedApplicationEntity t) throws DaoException {
		int result = 0;
		try (Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_CREATE_CLOSED_APPLICATION)){
			statement.setInt(1, t.getClosedApplicationId());
			statement.setInt(2, t.getApplicationId());
			statement.setInt(3, t.getApplicantId());
			statement.setDate(4, t.getDate());
			result = statement.executeUpdate();
		}catch(SQLException e) {
			logger.error("Problem at create method at ClosedApplicationDaoImpl", e);
			throw new DaoException("creation failed at ClosedApplicationDaoImpl", e);
		}
		return result>0;
	}

	@Override
	public ClosedApplicationEntity update(ClosedApplicationEntity t) throws DaoException {
		ClosedApplicationEntity closedApplication = new ClosedApplicationEntity();
		closedApplication = findById(t.getClosedApplicationId());
		try (Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_STATUS)){
			statement.setInt(1, t.getClosedApplicationId());
			statement.setInt(2, t.getApplicationId());
			statement.setInt(3, t.getApplicantId());
			statement.setDate(4, t.getDate());
			statement.executeUpdate();
		}catch(SQLException e) {
			logger.error("Problem at update method at ClosedApplicationDaoImpl", e);
			throw new DaoException("update failed at ClosedApplicationDaoImpl", e);
		}
		return closedApplication;
	}

	@Override
	public List<ClosedApplicationEntity> findByApplicantId(int applicantId) throws DaoException {
		List<ClosedApplicationEntity> closedApplications = new ArrayList<>();
		try (Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_APPLICANT_ID)){			
			statement.setInt(1, applicantId);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {	
				ClosedApplicationEntity closedApplication = buildClosedApplication(resultSet);
				closedApplications.add(closedApplication);
			}	
			}catch(SQLException e) {
				logger.error("Problem at update method at ClosedApplicationDaoImpl", e);
				throw new DaoException("problem at ClosedApplicationDaoImpl: findByStatus", e);
			}	
		return closedApplications;
	}

	private ClosedApplicationEntity buildClosedApplication(ResultSet resultSet) throws SQLException{
		ClosedApplicationEntity closedApplication = new ClosedApplicationEntity.ClosedApplicationsBuilder()
				.setClosedApplicationId(resultSet.getInt(CLOSED_APPLICATION_ID))
				.setApplicationId(resultSet.getInt(FK_APPLICATION_ID))
				.setApplicantId(resultSet.getInt(CLOSED_APPLICATIONS_APPLICANT_ID))
				.setDate(resultSet.getDate(DATE))
				.build();
		return closedApplication;
	}
	
}
