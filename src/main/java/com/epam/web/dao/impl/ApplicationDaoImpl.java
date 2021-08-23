package com.epam.web.dao.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.epam.web.connection.ConnectionFactory;
import com.epam.web.dao.ApplicationDao;
import com.epam.web.entity.ApplicationEntity;
import com.epam.web.entity.ApplicationTypeEntity;
import com.epam.web.exception.DaoException;

public class ApplicationDaoImpl implements ApplicationDao{
	
	private static final String SQL_FIND_ALL = """
			SELECT 
			applications.applicationId,
			status.status,
		    applicants.organizationName,
		    application_types.type,
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
		    join status on applications.statusId = status.statusId
		    join applicants on applications.applicantId = applicants.applicantId
		    join application_types on applications.typeId = application_types.typeId;
			""";
	private static final String SQL_FIND_BY_ID = """
			SELECT 
			applications.applicationId,
			status.status,
		    applicants.organizationName,
		    application_types.type,
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
		    join status on applications.statusId = status.statusId
		    join applicants on applications.applicantId = applicants.applicantId
		    join application_types on applications.typeId = application_types.typeId
		    where applications.applicationId = ?;
			""";
	private static final String SQL_DELETE_BY_ID = """ 
			delete from mydb.applications where applications.applicationId = ?
			""";
	private static final String SQL_INSERT_APPLICATION = """
			insert into applicants(applications.applicationId,
			status.status,
		    applicants.organizationName,
		    application_types.type,
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
				application.setApplicationId(resultSet.getInt("applications.applicationId"));
				application.setApplicationStatus(resultSet.getString("status.status"));
				application.setOrganizationName(resultSet.getString("applicants.organizationName"));
				application.setApplicationType(resultSet.getString("application_types.type"));
				application.setPhoto(resultSet.getBytes("applications.photo"));
				application.setName(resultSet.getString("applications.name"));
				application.setSurname(resultSet.getString("applications.surname"));
				application.setTraits(resultSet.getString("applications.traits"));
				application.setWeight(resultSet.getInt("applications.weight"));
				application.setHeight(resultSet.getInt("applications.height"));
				application.setDescription(resultSet.getString("applications.description"));
				application.setReward(resultSet.getInt("applications.reward"));
				application.setExpirationDate(resultSet.getDate("applications.expirationDate"));
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
				applicationAtFindById.setApplicationId(resultSet.getInt("applications.applicationId"));
				applicationAtFindById.setApplicationStatus(resultSet.getString("status.status"));
				applicationAtFindById.setOrganizationName(resultSet.getString("applicants.organizationName"));
				applicationAtFindById.setApplicationType(resultSet.getString("application_types.type"));
				applicationAtFindById.setPhoto(resultSet.getBytes("applications.photo"));
				applicationAtFindById.setName(resultSet.getString("applications.name"));
				applicationAtFindById.setSurname(resultSet.getString("applications.surname"));
				applicationAtFindById.setTraits(resultSet.getString("applications.traits"));
				applicationAtFindById.setWeight(resultSet.getInt("applications.weight"));
				applicationAtFindById.setHeight(resultSet.getInt("applications.height"));
				applicationAtFindById.setDescription(resultSet.getString("applications.description"));
				applicationAtFindById.setReward(resultSet.getInt("applications.reward"));
				applicationAtFindById.setExpirationDate(resultSet.getDate("applications.expirationDate"));
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
			statement = connection.prepareStatement(SQL_INSERT_APPLICANT);
			statement.setString(1, t.getOrganizationName());
			statement.setString(2, t.getLogin());
			statement.setString(3, t.getPassword());
			statement.setString(4, t.getPhone());
			statement.setString(5, t.getEmail());
			result = statement.executeUpdate();
		}catch(SQLException e) {
			throw new DaoException("creation failed at ApplicantDaoImpl", e);
		}finally {
			close(statement);
			close(connection);
		}
		return result>0;
		return false;
	}

	@Override
	public ApplicationEntity update(ApplicationEntity t) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ApplicationEntity> findbyStatus(String status) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ApplicationEntity> findbyOrganizationName(String name) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

}
