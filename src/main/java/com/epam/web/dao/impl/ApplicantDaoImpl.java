package com.epam.web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.epam.web.connection.ConnectionFactory;
import com.epam.web.dao.ApplicantDao;
import com.epam.web.entity.ApplicantEntity;
import com.epam.web.exception.DaoException;


public class ApplicantDaoImpl implements ApplicantDao{
	
	private static final String SQL_SELECT_ORGANIZATION_BY_NAME = """
			select applicantId, organizationName, login, password, email, phone 
			from applicants
			where organizationName = ?
			""";
	private static final String SQL_SELECT_ALL_APPLICANTS = """ 
			select applicantId, organizationName, login, password, phone, email 
			from applicants
			""";
	private static final String SQL_DELETE_BY_ID = """ 
			delete from applicants where applicantId = ?
			""";
	private static final String SQL_FIND_BY_ID = """
			select applicationId, organizationName, login, password, email, phone from applicants
			where applicantId = ?
			""";
	private static final String SQL_INSERT_APPLICANT = """
			insert into applicants(applicantId, organizatoinName, login, password, email, phone) values(NULL,?,?,?,?,?)
			""";
	private static final String SQL_UPDATE_APPLICANT = """
			update applicants set applicantId = ?, organizationName = ?, login = ?, password = ?, email = ?, phone = ?
			where applicantId = ?
			""";

	@Override
	public List<ApplicantEntity> findAll() throws DaoException{
		List<ApplicantEntity> applicants = new ArrayList<>();
		Connection connection = null;
		Statement statement = null;
		try {
			connection = ConnectionFactory.createConnection();
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_APPLICANTS);
			while(resultSet.next()) {
				ApplicantEntity applicant = new ApplicantEntity();
				applicant.setId(resultSet.getInt("applicantId"));
				applicant.setOrganizationName(resultSet.getString("organizationName"));
				applicant.setLogin(resultSet.getString("login"));
				applicant.setPassword(resultSet.getString("password"));
				applicant.setPhone(resultSet.getString("phone"));
				applicant.setEmail(resultSet.getString("email"));
				applicants.add(applicant);
			}
		}catch (SQLException e) {
			throw new DaoException(e);
		}finally {
			close(statement);
			close(connection);
		}
		return applicants;
	}

	@Override
	public ApplicantEntity findById(Integer id) throws DaoException {
		Connection connection = null;
		PreparedStatement statement = null;
		ApplicantEntity applicant = new ApplicantEntity();
		try {			
			connection = ConnectionFactory.createConnection();
			statement = connection.prepareStatement(SQL_FIND_BY_ID);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {	
				applicant.setId(resultSet.getInt("applicantId"));
				applicant.setOrganizationName(resultSet.getString("organizationName"));
				applicant.setLogin(resultSet.getString("login"));
				applicant.setPassword(resultSet.getString("password"));
				applicant.setEmail(resultSet.getString("email"));
				applicant.setPhone(resultSet.getString("phone"));
			}	
			}catch(SQLException e) {
				throw new DaoException("problem at Applicant DAO: findById", e);
			}finally {
				close(statement);
				close(connection);
			}		
		return applicant;
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
			throw new DaoException("error occured during delete by id at ApplicantDaoImpl", e);
		}finally {
			close(statement);
			close(connection);
		}
		return result>0;
	}

	@Override
	public boolean delete(ApplicantEntity t) throws DaoException {
		int result = 0;
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = ConnectionFactory.createConnection();
			statement = connection.prepareStatement(SQL_DELETE_BY_ID);
			statement.setInt(1, t.getId());
			result = statement.executeUpdate();
		}catch(SQLException e) {
			throw new DaoException("error ocured during deletion entity at ApplicantDaoImpl",e);
		}finally {
			close(statement);
			close(connection);
		}
		return result>0;
	}

	@Override
	public boolean create(ApplicantEntity t) throws DaoException {
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
	}

	@Override
	public ApplicantEntity update(ApplicantEntity t) throws DaoException {
		ApplicantEntity applicant = new ApplicantEntity();
		Connection connection = null;
		PreparedStatement statement = null;
		applicant = findById(t.getId());
		try {
			connection = ConnectionFactory.createConnection();
			statement = connection.prepareStatement(SQL_UPDATE_APPLICANT);			
			statement.setString(1, t.getOrganizationName());
			statement.setString(2, t.getLogin());
			statement.setString(3, t.getPassword());
			statement.setString(4, t.getEmail());
			statement.setString(5, t.getPhone());
			statement.setInt(6, t.getId());
			statement.executeUpdate();
		}catch(SQLException e) {
			throw new DaoException("error occured during update at ApplicantDaoImpl");
		}finally {
			close(statement);
			close(connection);
		}
		return applicant;
	}

	@Override
	public List<ApplicantEntity> findAllByOrganizationName(String name) throws DaoException{
		List<ApplicantEntity> applicatns = new ArrayList<>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = ConnectionFactory.createConnection();
			preparedStatement = connection.prepareStatement(SQL_SELECT_ORGANIZATION_BY_NAME);
			preparedStatement.setString(1, name);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				ApplicantEntity applicant = new ApplicantEntity();
				applicant.setId(resultSet.getInt("applicantId"));
				applicant.setOrganizationName(resultSet.getString("organizationName"));
				applicant.setLogin(resultSet.getString("login"));
				applicant.setPassword(resultSet.getString("password"));
				applicant.setEmail(resultSet.getString("email"));
				applicant.setPhone(resultSet.getString("phone"));
				applicatns.add(applicant);
			}
		}catch(SQLException e) {
			throw new DaoException(e);
		}finally {
			close(preparedStatement);
			close(connection);
		}
		return applicatns;
	}

}
