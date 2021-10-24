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


// TODO: Auto-generated Javadoc
/**
 * The Class ApplicantDaoImpl.
 */
public class ApplicantDaoImpl implements ApplicantDao{
	
	/** The Constant logger. */
	private static final Logger logger = LogManager.getLogger();
	
	/** The instance. */
	private static ApplicantDaoImpl instance = new ApplicantDaoImpl();
	
	/** The connection pool. */
	private ConnectionPool connectionPool = ConnectionPool.getInstance();
	
	
	/**
	 * Instantiates a new applicant dao impl.
	 */
	private ApplicantDaoImpl() {
		
	}
	
	/**
	 * Gets the single instance of ApplicantDaoImpl.
	 *
	 * @return single instance of ApplicantDaoImpl
	 */
	public static ApplicantDaoImpl getInstance() {
		return instance;
	}
	
	/** The Constant SQL_SELECT_ORGANIZATION_BY_NAME. */
	private static final String SQL_SELECT_ORGANIZATION_BY_NAME = """
			select applicantId, organizationName, login, password, email, phone 
			from applicants
			where organizationName = ?
			""";
	
	/** The Constant SQL_SELECT_ORGANIZATION_BY_LOGIN. */
	private static final String SQL_SELECT_ORGANIZATION_BY_LOGIN = """
			select applicantId, organizationName, login, password, email, phone 
			from applicants
			where login = ?
			""";
	
	/** The Constant SQL_SELECT_ALL_APPLICANTS. */
	private static final String SQL_SELECT_ALL_APPLICANTS = """ 
			select applicantId, organizationName, login, password, phone, email 
			from applicants
			""";
	
	/** The Constant SQL_DELETE_BY_ID. */
	private static final String SQL_DELETE_BY_ID = """ 
			delete from applicants where applicantId = ?
			""";
	
	/** The Constant SQL_FIND_BY_ID. */
	private static final String SQL_FIND_BY_ID = """
			select applicantId, organizationName, login, password, email, phone from applicants
			where applicantId = ?
			""";
	
	/** The Constant SQL_INSERT_APPLICANT. */
	private static final String SQL_INSERT_APPLICANT = """
			insert into applicants(applicantId, organizationName, login, password, email, phone) values(NULL,?,?,?,?,?)
			""";
	
	/** The Constant SQL_UPDATE_APPLICANT. */
	private static final String SQL_UPDATE_APPLICANT = """
			update applicants set  organizationName = ?, login = ?, password = ?, email = ?, phone = ?
			where applicantId = ?
			""";

	/**
	 * Find all.
	 *
	 * @return the list
	 * @throws DaoException the dao exception
	 */
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

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the applicant
	 * @throws DaoException the dao exception
	 */
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

	/**
	 * Delete.
	 *
	 * @param id the id
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
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

	/**
	 * Delete.
	 *
	 * @param t the t
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	@Override
	public boolean delete(Applicant applicant) throws DaoException {
		int result = 0;
		try (Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_DELETE_BY_ID)){
			statement.setInt(1, applicant.getId());
			result = statement.executeUpdate();
		}catch(SQLException e) {
			logger.error("Problem at delete(ENTITY) method at ApplicantDaoImpl", e);
			throw new DaoException("error ocured during deletion entity at ApplicantDaoImpl",e);
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
	public boolean create(Applicant applicant) throws DaoException {
		int result = 0;
		try(Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_INSERT_APPLICANT)) {			
			statement.setString(1, applicant.getOrganizationName());
			statement.setString(2, applicant.getLogin());
			statement.setString(3, applicant.getPassword());
			statement.setString(4, applicant.getEmail());
			statement.setString(5, applicant.getPhone());
			result = statement.executeUpdate();
		}catch(SQLException e) {
			logger.error("Problem at create method at ApplicantDaoImpl", e);
			throw new DaoException("creation failed at ApplicantDaoImpl", e);
		}
		return result>0;
	}

	/**
	 * Update.
	 *
	 * @param applicant the applicant
	 * @throws DaoException the dao exception
	 */
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

	/**
	 * Find by organization name.
	 *
	 * @param name the name
	 * @return the applicant
	 * @throws DaoException the dao exception
	 */
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
	
	/**
	 * Find organization by login.
	 *
	 * @param login the login
	 * @return the applicant
	 * @throws DaoException the dao exception
	 */
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

	/**
	 * Builds the applicant.
	 *
	 * @param resultSet the result set
	 * @return the applicant
	 * @throws SQLException the SQL exception
	 */
	private Applicant buildApplicant(ResultSet resultSet) throws SQLException{
		return new Applicant.ApplicantBuilder()
				.setId(resultSet.getInt(APPLICANT_ID))
				.setOrganizationName(resultSet.getString(ORGANIZATION_NAME))
				.setLogin(resultSet.getString(APPLICANT_LOGIN))
				.setPassword(resultSet.getString(APPLICANT_PASSWORD))
				.setEmail(resultSet.getString(APPLICANT_EMAIL))
				.setPhone(resultSet.getString(APPLICANT_PHONE))
				.build();
		 
	}

	

}
