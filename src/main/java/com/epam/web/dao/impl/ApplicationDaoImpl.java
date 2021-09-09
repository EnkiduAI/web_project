package com.epam.web.dao.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.epam.web.dao.TableColumns;

import com.epam.web.connection.ConnectionFactory;
import com.epam.web.dao.ApplicationDao;
import com.epam.web.entity.ApplicationEntity;
import com.epam.web.exception.DaoException;

public class ApplicationDaoImpl implements ApplicationDao{
	
	private static final String SQL_FIND_ALL = """
			SELECT 
			applications.applicationId,
			applications.statusId,
		    applications.applicantId,
		    applications.typeId,
		    applications.photo,
		    applications.name,
		    applications.surname,
		    applications.traits,
		    applications.weight,
		    applications.height,
		    applications.description,
		    applications.reward,
		    applications.expirationDate
			FROM
		    mydb.applications;
			""";
	private static final String SQL_FIND_BY_ID = """
			SELECT 
			applications.applicationId,
			applications.statusId,
		    applications.applicantId,
		    applications.typeId,
		    applications.photo,
		    applications.name,
		    applications.surname,
		    applications.traits,
		    applications.weight,
		    applications.height,
		    applications.description,
		    applications.reward,
		    applications.expirationDate
			FROM
		    mydb.applications
		    where applications.applicationId = ?;
			""";
	private static final String SQL_DELETE_BY_ID = """ 
			delete from mydb.applications where applications.applicationId = ?
			""";
	private static final String SQL_INSERT_APPLICATION = """
			insert into mydb.applicants
			(applications.applicationId,
			applications.statusId,
		    applications.applicantId,
		    applications.typeId,
		    applications.photo,
		    applications.name,
		    applications.surname,
		    applications.traits,
		    applications.weight,
		    applications.height,
		    applications.description,
		    applications.reward,
		    applications.expirationDate) 
		    values(NULL,?,?,?,?,?,?,?,?,?,?,?,?)
			""";
	private static final String SQL_UPDATE_APPLICATION = """
			update mydb.applications set
			applications.applicationId = ?,
			applications.statusId = ?,
		    applications.applicantId = ?,
		    applications.typeId = ?,
		    applications.photo = ?,
		    applications.name= ?,
		    applications.surname = ?,
		    applications.traits = ?,
		    applications.weight = ?,
		    applications.height = ?,
		    applications.description = ?,
		    applications.reward = ?,
		    applications.expirationDate = ?;
			""";
	private static final String SQL_FIND_BY_STATUS = """
			SELECT 
			applications.applicationId,
			applications.statusId,
		    applications.applicantId,
		    applications.typeId,
		    applications.photo,
		    applications.name,
		    applications.surname,
		    applications.traits,
		    applications.weight,
		    applications.height,
		    applications.description,
		    applications.reward,
		    applications.expirationDate
			FROM
		    mydb.applications
		    where applications.statusId = ?;
			""";
	private static final String SQL_FIND_BY_ORGANIZATION_NAME = """
			SELECT 
			applications.applicationId,
			applications.statusId,
		    applications.applicantId,
		    applications.typeId,
		    applications.photo,
		    applications.name,
		    applications.surname,
		    applications.traits,
		    applications.weight,
		    applications.height,
		    applications.description,
		    applications.reward,
		    applications.expirationDate
			FROM
		    mydb.applications
		    where applications.organizationName = ?;
			""";

	@Override
	public List<ApplicationEntity> findAll() throws DaoException {
		List<ApplicationEntity> applications = new ArrayList<>();
		Connection connection = null;
		Statement statement = null;
		try {
			connection = ConnectionFactory.createConnection();
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(SQL_FIND_ALL);
			while(resultSet.next()) {
				ApplicationEntity application = new ApplicationEntity();
				application.setApplicationId(resultSet.getInt(TableColumns.APPLICATION_ID));
				application.setStatusId(resultSet.getInt(TableColumns.FK_STATUS_ID));
				application.setApplicantId(resultSet.getInt(TableColumns.APPLICANT_ID));
				application.setTypeId(resultSet.getInt(TableColumns.FK_TYPE_ID));
				application.setPhoto(resultSet.getBytes(TableColumns.PHOTO));
				application.setName(resultSet.getString(TableColumns.NAME));
				application.setSurname(resultSet.getString(TableColumns.SURNAME));
				application.setTraits(resultSet.getString(TableColumns.TRAITS));
				application.setWeight(resultSet.getInt(TableColumns.WEIGHT));
				application.setHeight(resultSet.getInt(TableColumns.HEIGHT));
				application.setDescription(resultSet.getString(TableColumns.DESCRIPTION));
				application.setReward(resultSet.getInt(TableColumns.REWARD));
				application.setExpirationDate(resultSet.getDate(TableColumns.EXPIRATION_DATE));
				applications.add(application);
			}
		}catch (SQLException e) {
			throw new DaoException("error ocurred at findAll: ApplicationDaoImpl", e);
		}finally {
			close(statement);
			close(connection);
		}
		return applications;
	}

	@Override
	public ApplicationEntity findById(Integer id) throws DaoException {
		Connection connection = null;
		PreparedStatement statement = null;
		ApplicationEntity applicationAtFindById = new ApplicationEntity();
		try {			
			connection = ConnectionFactory.createConnection();
			statement = connection.prepareStatement(SQL_FIND_BY_ID);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {	
				applicationAtFindById.setApplicationId(resultSet.getInt(TableColumns.APPLICATION_ID));
				applicationAtFindById.setStatusId(resultSet.getInt(TableColumns.FK_STATUS_ID));
				applicationAtFindById.setApplicantId(resultSet.getInt(TableColumns.FK_APPLICANT_ID));
				applicationAtFindById.setTypeId(resultSet.getInt(TableColumns.FK_TYPE_ID));
				applicationAtFindById.setPhoto(resultSet.getBytes(TableColumns.PHOTO));
				applicationAtFindById.setName(resultSet.getString(TableColumns.NAME));
				applicationAtFindById.setSurname(resultSet.getString(TableColumns.SURNAME));
				applicationAtFindById.setTraits(resultSet.getString(TableColumns.TRAITS));
				applicationAtFindById.setWeight(resultSet.getInt(TableColumns.WEIGHT));
				applicationAtFindById.setHeight(resultSet.getInt(TableColumns.HEIGHT));
				applicationAtFindById.setDescription(resultSet.getString(TableColumns.DESCRIPTION));
				applicationAtFindById.setReward(resultSet.getInt(TableColumns.REWARD));
				applicationAtFindById.setExpirationDate(resultSet.getDate(TableColumns.EXPIRATION_DATE));
			}	
			}catch(SQLException e) {
				throw new DaoException("problem at ApplicationDao: findById", e);
			}finally {
				close(statement);
				close(connection);
			}		
		return applicationAtFindById;
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
			throw new DaoException("error occured during delete by id at ApplicationDaoImpl", e);
		}finally {
			close(statement);
			close(connection);
		}
		return result>0;
	}

	@Override
	public boolean delete(ApplicationEntity t) throws DaoException {
		int result = 0;
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = ConnectionFactory.createConnection();
			statement = connection.prepareStatement(SQL_DELETE_BY_ID);
			statement.setInt(1, t.getApplicationId());
			result = statement.executeUpdate();
		}catch(SQLException e) {
			throw new DaoException("error ocured during deletion entity at ApplicatonDaoImpl",e);
		}finally {
			close(statement);
			close(connection);
		}
		return result>0;
	}

	@Override
	public boolean create(ApplicationEntity t) throws DaoException {
		int result = 0;
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = ConnectionFactory.createConnection();
			statement = connection.prepareStatement(SQL_INSERT_APPLICATION);
			statement.setInt(1, t.getApplicationId());
			statement.setInt(2, t.getStatusId());
			statement.setInt(3, t.getApplicantId());
			statement.setBytes(4, t.getPhoto());
			statement.setString(5, t.getName());
			statement.setString(6, t.getSurname());
			statement.setString(7, t.getTraits());
			statement.setInt(8, t.getWeight());
			statement.setInt(9, t.getHeight());
			statement.setString(10, t.getDescription());
			statement.setInt(11, t.getReward());
			statement.setDate(12, t.getExpirationDate());
			result = statement.executeUpdate();
		}catch(SQLException e) {
			throw new DaoException("creation failed at ApplicantDaoImpl", e);
		}finally {
			close(statement);
			close(connection);
		}
		return result>0;
	}

	@Override
	public ApplicationEntity update(ApplicationEntity t) throws DaoException {
		ApplicationEntity application = new ApplicationEntity();
		Connection connection = null;
		PreparedStatement statement = null;
		application = findById(t.getApplicationId());
		try {
			connection = ConnectionFactory.createConnection();
			statement = connection.prepareStatement(SQL_UPDATE_APPLICATION);
			statement.setInt(2, t.getStatusId());
			statement.setInt(3, t.getApplicantId());
			statement.setBytes(4, t.getPhoto());
			statement.setString(5, t.getName());
			statement.setString(6, t.getSurname());
			statement.setString(7, t.getTraits());
			statement.setInt(8, t.getWeight());
			statement.setInt(9, t.getHeight());
			statement.setString(10, t.getDescription());
			statement.setInt(11, t.getReward());
			statement.setDate(12, t.getExpirationDate());
			statement.executeUpdate();
		}catch(SQLException e) {
			throw new DaoException("update failed at ApplicantDaoImpl", e);
		}finally {
			close(statement);
			close(connection);
		}
		return application;
	}

	@Override
	public List<ApplicationEntity> findbyStatus(int statusId) throws DaoException {
		Connection connection = null;
		PreparedStatement statement = null;
		List<ApplicationEntity> applicationAtFindByStatus = new ArrayList<>();
		try {			
			connection = ConnectionFactory.createConnection();
			statement = connection.prepareStatement(SQL_FIND_BY_STATUS);
			statement.setInt(1, statusId);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				ApplicationEntity application = new ApplicationEntity();
				application.setApplicationId(resultSet.getInt(TableColumns.APPLICATION_ID));
				application.setStatusId(resultSet.getInt(TableColumns.FK_STATUS_ID));
				application.setApplicantId(resultSet.getInt(TableColumns.FK_APPLICANT_ID));
				application.setTypeId(resultSet.getInt(TableColumns.FK_TYPE_ID));
				application.setPhoto(resultSet.getBytes(TableColumns.PHOTO));
				application.setName(resultSet.getString(TableColumns.NAME));
				application.setSurname(resultSet.getString(TableColumns.SURNAME));
				application.setTraits(resultSet.getString(TableColumns.TRAITS));
				application.setWeight(resultSet.getInt(TableColumns.WEIGHT));
				application.setHeight(resultSet.getInt(TableColumns.HEIGHT));
				application.setDescription(resultSet.getString(TableColumns.DESCRIPTION));
				application.setReward(resultSet.getInt(TableColumns.REWARD));
				application.setExpirationDate(resultSet.getDate(TableColumns.EXPIRATION_DATE));
				applicationAtFindByStatus.add(application);
			}	
			}catch(SQLException e) {
				throw new DaoException("problem at ApplicationDao: findById", e);
			}finally {
				close(statement);
				close(connection);
			}		
		return applicationAtFindByStatus;
	}

	@Override
	public List<ApplicationEntity> findbyOrganizationName(int organizationName) throws DaoException {
		Connection connection = null;
		PreparedStatement statement = null;
		List<ApplicationEntity> applicationAtFindByOrganizationName = new ArrayList<>();
		try {			
			connection = ConnectionFactory.createConnection();
			statement = connection.prepareStatement(SQL_FIND_BY_ORGANIZATION_NAME);
			statement.setInt(1, organizationName);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				ApplicationEntity application = new ApplicationEntity();
				application.setApplicationId(resultSet.getInt(TableColumns.APPLICATION_ID));
				application.setStatusId(resultSet.getInt(TableColumns.FK_STATUS_ID));
				application.setApplicantId(resultSet.getInt(TableColumns.FK_APPLICANT_ID));
				application.setTypeId(resultSet.getInt(TableColumns.FK_TYPE_ID));
				application.setPhoto(resultSet.getBytes(TableColumns.PHOTO));
				application.setName(resultSet.getString(TableColumns.NAME));
				application.setSurname(resultSet.getString(TableColumns.SURNAME));
				application.setTraits(resultSet.getString(TableColumns.TRAITS));
				application.setWeight(resultSet.getInt(TableColumns.WEIGHT));
				application.setHeight(resultSet.getInt(TableColumns.HEIGHT));
				application.setDescription(resultSet.getString(TableColumns.DESCRIPTION));
				application.setReward(resultSet.getInt(TableColumns.REWARD));
				application.setExpirationDate(resultSet.getDate(TableColumns.EXPIRATION_DATE));
				applicationAtFindByOrganizationName.add(application);
			}	
			}catch(SQLException e) {
				throw new DaoException("problem at ApplicationDao: findById", e);
			}finally {
				close(statement);
				close(connection);
			}		
		return applicationAtFindByOrganizationName;
	}

}
