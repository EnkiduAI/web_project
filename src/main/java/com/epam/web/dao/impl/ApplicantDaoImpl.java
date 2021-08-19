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
			select applicantId, login, email, phone from applicants
			where organizationName = ?  
			""";
	private static final String SQL_SELECT_ALL_APPLICANTS = """ 
			select applicantId, organizationName, phone, email from applicants
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
				applicant.setId(resultSet.getInt("applicationId"));
				applicant.setOrganizationName(resultSet.getString("organizationName"));
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
	public ApplicantEntity findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(ApplicantEntity t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean create(ApplicantEntity t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ApplicantEntity update(ApplicantEntity t) {
		// TODO Auto-generated method stub
		return null;
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
				applicant.setLogin(resultSet.getString("login"));
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
