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
import com.epam.web.model.dao.ApplicantDao;
import com.epam.web.model.entity.Applicant;


public class ApplicantDaoImpl implements ApplicantDao{
	private static final Logger logger = LogManager.getLogger();
	private static ApplicantDaoImpl instance = new ApplicantDaoImpl();
	private ConnectionPool connectionPool = ConnectionPool.getInstance();
	
	
	private ApplicantDaoImpl() {
		
	}
	
	public static ApplicantDaoImpl getInstance() {
		return instance;
	}
	
	private static final String SQL_SELECT_ORGANIZATION_BY_NAME = """
			select applicantId, organizationName, login, password, email, phone 
			from applicants
			where organizationName = ?
			""";
	private static final String SQL_SELECT_ORGANIZATION_BY_LOGIN = """
			select applicantId, organizationName, login, password, email, phone 
			from applicants
			where login = ?
			""";
	private static final String SQL_SELECT_ALL_APPLICANTS = """ 
			select applicantId, organizationName, login, password, phone, email 
			from applicants
			""";
	private static final String SQL_DELETE_BY_ID = """ 
			delete from applicants where applicantId = ?
			""";
	private static final String SQL_FIND_BY_ID = """
			select applicantId, organizationName, login, password, email, phone from applicants
			where applicantId = ?
			""";
	private static final String SQL_INSERT_APPLICANT = """
			insert into applicants(applicantId, organizationName, login, password, email, phone) values(NULL,?,?,?,?,?)
			""";
	private static final String SQL_UPDATE_APPLICANT = """
			update applicants set  organizationName = ?, login = ?, password = ?, email = ?, phone = ?
			where applicantId = ?
			""";

	@Override
	public List<Applicant> findAll() throws DaoException{
		List<Applicant> applicants = new ArrayList<>();	
		try (Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL_APPLICANTS)){
			try(ResultSet resultSet = statement.executeQuery()){
			while(resultSet.next()) {
				Applicant applicant = buildApplicant(resultSet);				
				applicants.add(applicant);
			}
			}
		}catch (SQLException e) {
			logger.error("Problem at findAll method at ApplicantDaoImpl", e);
			throw new DaoException(e);
		}
		return applicants;
	}

	@Override
	public Applicant findById(Integer id) throws DaoException {
		
		Applicant applicant = new Applicant();
		try(Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ID))
		{			
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {	
				applicant = buildApplicant(resultSet);
			}	
			}catch(SQLException e) {
				logger.error("Problem at findById method at ApplicantDaoImpl", e);
				throw new DaoException("problem at Applicant DAO: findById", e);
			}		
		return applicant;
	}

	@Override
	public boolean delete(Integer id) throws DaoException {
		int result = 0;
		try (Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_DELETE_BY_ID)){
			statement.setInt(1, id);
			result = statement.executeUpdate();
		}catch(SQLException e) {
			logger.error("Problem at delete(ID) method at ApplicantDaoImpl", e);
			throw new DaoException("error occured during delete by id at ApplicantDaoImpl", e);
		}
		return result>0;
	}

	@Override
	public boolean delete(Applicant t) throws DaoException {
		int result = 0;
		try (Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_DELETE_BY_ID)){
			statement.setInt(1, t.getId());
			result = statement.executeUpdate();
		}catch(SQLException e) {
			logger.error("Problem at delete(ENTITY) method at ApplicantDaoImpl", e);
			throw new DaoException("error ocured during deletion entity at ApplicantDaoImpl",e);
		}
		return result>0;
	}

	@Override
	public boolean create(Applicant t) throws DaoException {
		int result = 0;
		try(Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_INSERT_APPLICANT)) {			
			statement.setString(1, t.getOrganizationName());
			statement.setString(2, t.getLogin());
			statement.setString(3, t.getPassword());
			statement.setString(4, t.getEmail());
			statement.setString(5, t.getPhone());
			result = statement.executeUpdate();
		}catch(SQLException e) {
			logger.error("Problem at create method at ApplicantDaoImpl", e);
			throw new DaoException("creation failed at ApplicantDaoImpl", e);
		}
		return result>0;
	}

	@Override
	public void update(Applicant applicant) throws DaoException {	
		try (Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_APPLICANT)){			
			statement.setString(1, applicant.getOrganizationName());
			statement.setString(2, applicant.getLogin());
			statement.setString(3, applicant.getPassword());
			statement.setString(4, applicant.getEmail());
			statement.setString(5, applicant.getPhone());
			statement.setInt(6, applicant.getId());
			statement.executeUpdate();
		}catch(SQLException e) {
			logger.error("Problem at update method at ApplicantDaoImpl", e);
			throw new DaoException("error occured during update at ApplicantDaoImpl");
		}
		
	}

	@Override
	public Applicant findByOrganizationName(String name) throws DaoException{
		Applicant applicant = new Applicant();
		try(Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ORGANIZATION_BY_NAME)) {
			statement.setString(1, name);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				applicant = buildApplicant(resultSet);
			}
		}catch(SQLException e) {
			logger.error("Problem at findAllByOrganizationName method at ApplicantDaoImpl", e);
			throw new DaoException(e);
		}
		return applicant;
	}
	
	@Override
	public Applicant findOrganizationByLogin(String login) throws DaoException {
		Applicant applicant = new Applicant();
		try(Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ORGANIZATION_BY_LOGIN)) {
			statement.setString(1, login);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				applicant = buildApplicant(resultSet);
			}
		}catch(SQLException e) {
			logger.error("Problem at findAllByOrganizationLogin method at ApplicantDaoImpl", e);
			throw new DaoException(e);
		}
		return applicant;
	}

	private Applicant buildApplicant(ResultSet resultSet) throws SQLException{
		Applicant applicant = new Applicant.ApplicantBuilder()
				.setId(resultSet.getInt(APPLICANT_ID))
				.setOrganizationName(resultSet.getString(ORGANIZATION_NAME))
				.setLogin(resultSet.getString(APPLICANT_LOGIN))
				.setPassword(resultSet.getString(APPLICANT_PASSWORD))
				.setEmail(resultSet.getString(APPLICANT_EMAIL))
				.setPhone(resultSet.getString(APPLICANT_PHONE))
				.build();
		return applicant;
	}

	

}
