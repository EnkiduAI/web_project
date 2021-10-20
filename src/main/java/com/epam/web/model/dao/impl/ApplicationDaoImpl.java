/*
 * 
 */
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
import com.epam.web.model.dao.ApplicationDao;
import com.epam.web.model.entity.Applicant;
import com.epam.web.model.entity.Application;

// TODO: Auto-generated Javadoc
/**
 * The Class ApplicationDaoImpl.
 */
public class ApplicationDaoImpl implements ApplicationDao {

	/** The Constant logger. */
	private static final Logger logger = LogManager.getLogger();

	/** The instance. */
	private static ApplicationDaoImpl instance = new ApplicationDaoImpl();

	/** The connection pool. */
	private ConnectionPool connectionPool = ConnectionPool.getInstance();

	/**
	 * Instantiates a new application dao impl.
	 */
	private ApplicationDaoImpl() {

	}

	/**
	 * Gets the single instance of ApplicationDaoImpl.
	 *
	 * @return single instance of ApplicationDaoImpl
	 */
	public static ApplicationDaoImpl getInstance() {
		return instance;
	}

	/** The Constant SQL_FIND_ALL. */
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

	/** The Constant SQL_FIND_BY_ID. */
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

	/** The Constant SQL_DELETE_BY_ID. */
	private static final String SQL_DELETE_BY_ID = """
			delete from mydb.applications where applications.applicationId = ?
			""";

	private static final String SQL_DELETE_BY_APPLICANTID = """
			delete from mydb.applications where applications.applicantId = ?
			""";

	/** The Constant SQL_INSERT_APPLICATION. */
	private static final String SQL_INSERT_APPLICATION = """
			insert into mydb.applications
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

	/** The Constant SQL_UPDATE_APPLICATION. */
	private static final String SQL_UPDATE_APPLICATION = """
			update mydb.applications set
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
			   applications.expirationDate = ?
			   where applications.applicationId = ?;
			""";

	/** The Constant SQL_FIND_BY_STATUS. */
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
			applications.height,
			applications.weight,
			applications.description,
			applications.reward,
			applications.expirationDate
			FROM mydb.applications
			join mydb.status as st on st.statusId = applications.statusId
			where st.status = ?;
						""";

	/** The Constant SQL_FIND_BY_ORGANIZATION_NAME. */
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
			   join mydb.applicants as ap on ap.applicantId = applications.applicantId
			   where ap.organizationName = ?;
			""";

	/** The Constant SQL_FIND_UNPOSTED. */
	private static final String SQL_FIND_UNPOSTED = """
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
			FROM mydb.applications
			JOIN mydb.status as st on applications.statusId = st.statusId
			WHERE st.status != "POSTED";
						""";

	/** The Constant SQL_FIND_POSTED. */
	private static final String SQL_FIND_POSTED = """
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
			FROM mydb.applications
			JOIN mydb.status as st on applications.statusId = st.statusId
			WHERE st.status = "POSTED";
						""";
	
	private static final String SQL_FIND_UNPOSTED_BY_APPLICANT = """
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
FROM mydb.applications
JOIN mydb.status as st on applications.statusId = st.statusId
JOIN mydb.applicants as apls on applications.applicantId = apls.applicantId
WHERE st.status = "VERIFYING" and apls.organizationName = ?;
			""";
	private static final String SQL_FIND_POSTED_BY_APPLICANT = """
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
FROM mydb.applications
JOIN mydb.status as st on applications.statusId = st.statusId
JOIN mydb.applicants as apls on applications.applicantId = apls.applicantId
WHERE st.status = "POSTED" and apls.organizationName = ?;
			""";

	/**
	 * Find all.
	 *
	 * @return the list
	 * @throws DaoException the dao exception
	 */
	@Override
	public List<Application> findAll() throws DaoException {
		List<Application> applications = new ArrayList<>();
		try (Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL)) {
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Application application = buildApplication(resultSet);
				applications.add(application);
			}
		} catch (SQLException e) {
			logger.error("Problem at findAll method at ApplicationDaoImpl", e);
			throw new DaoException("error ocurred at findAll: ApplicationDaoImpl", e);
		}
		return applications;
	}

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the application entity
	 * @throws DaoException the dao exception
	 */
	@Override
	public Application findById(Integer id) throws DaoException {
		Application applicationAtFindById = new Application();
		try (Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ID)) {
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				applicationAtFindById = buildApplication(resultSet);
			}
		} catch (SQLException e) {
			logger.error("Problem at findById method at ApplicationDaoImpl", e);
			throw new DaoException("problem at ApplicationDao: findById", e);
		}
		return applicationAtFindById;
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
				PreparedStatement statement = connection.prepareStatement(SQL_DELETE_BY_ID)) {
			statement.setInt(1, id);
			result = statement.executeUpdate();
		} catch (SQLException e) {
			logger.error("Problem at delete method at ApplicationDaoImpl", e);
			throw new DaoException("error occured during delete(ID) by id at ApplicationDaoImpl", e);
		}
		return result > 0;
	}

	/**
	 * Delete.
	 *
	 * @param t the t
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	@Override
	public boolean delete(Application t) throws DaoException {
		int result = 0;

		try (Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_DELETE_BY_ID)) {
			statement.setInt(1, t.getApplicationId());
			result = statement.executeUpdate();
		} catch (SQLException e) {
			logger.error("Problem at delete(ENTITY) method at ApplicationDaoImpl", e);
			throw new DaoException("error ocured during deletion entity at ApplicatonDaoImpl", e);
		}
		return result > 0;
	}

	/**
	 * Creates the.
	 *
	 * @param t the t
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	@Override
	public boolean create(Application t) throws DaoException {
		int result = 0;
		try (Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_INSERT_APPLICATION)) {			
			statement.setInt(1, t.getStatusId());
			statement.setInt(2, t.getApplicantId());
			statement.setInt(3, t.getTypeId());
			statement.setString(4, t.getPhoto());
			statement.setString(5, t.getName());
			statement.setString(6, t.getSurname());
			statement.setString(7, t.getTraits());
			statement.setInt(8, t.getWeight());
			statement.setInt(9, t.getHeight());
			statement.setString(10, t.getDescription());
			statement.setInt(11, t.getReward());
			statement.setDate(12, t.getExpirationDate());
			result = statement.executeUpdate();
		} catch (SQLException e) {
			logger.error("Problem at create method at ApplicationDaoImpl", e);
			throw new DaoException("creation failed at ApplicantDaoImpl", e);
		}
		return result > 0;
	}

	/**
	 * Update.
	 *
	 * @param t the t
	 * @throws DaoException the dao exception
	 */
	@Override
	public void update(Application application) throws DaoException {
		try (Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_APPLICATION)) {
			statement.setInt(1, application.getStatusId());
			statement.setInt(2, application.getApplicantId());
			statement.setInt(3, application.getTypeId());
			statement.setString(4, application.getPhoto());
			statement.setString(5, application.getName());
			statement.setString(6, application.getSurname());
			statement.setString(7, application.getTraits());
			statement.setInt(8, application.getWeight());
			statement.setInt(9, application.getHeight());
			statement.setString(10, application.getDescription());
			statement.setInt(11, application.getReward());
			statement.setDate(12, application.getExpirationDate());
			statement.setInt(13, application.getApplicationId());
			statement.executeUpdate();
		} catch (SQLException e) {
			logger.error("Problem at update method at ApplicationDaoImpl", e);
			throw new DaoException("update failed at ApplicantDaoImpl", e);
		}

	}

	/**
	 * Findby status.
	 *
	 * @param status the status
	 * @return the list
	 * @throws DaoException the dao exception
	 */
	@Override
	public List<Application> findbyStatus(String status) throws DaoException {
		List<Application> applicationAtFindByStatus = new ArrayList<>();
		try (Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_STATUS)) {
			statement.setString(1, status);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Application application = buildApplication(resultSet);
				applicationAtFindByStatus.add(application);
			}
		} catch (SQLException e) {
			logger.error("Problem at findByStatus method at ApplicationDaoImpl", e);
			throw new DaoException("problem at ApplicationDao: findById", e);
		}
		return applicationAtFindByStatus;
	}

	/**
	 * Findby organization name.
	 *
	 * @param organizationName the organization name
	 * @return the list
	 * @throws DaoException the dao exception
	 */
	@Override
	public List<Application> findbyOrganizationName(String organizationName) throws DaoException {
		List<Application> applicationAtFindByOrganizationName = new ArrayList<>();
		try (Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ORGANIZATION_NAME)) {
			statement.setString(1, organizationName);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Application application = buildApplication(resultSet);
				applicationAtFindByOrganizationName.add(application);
			}
		} catch (SQLException e) {
			logger.error("Problem at findByOrganizationName method at ApplicationDaoImpl", e);
			throw new DaoException("problem at ApplicationDao: findById", e);
		}
		return applicationAtFindByOrganizationName;
	}

	/**
	 * Find unposted.
	 *
	 * @return the list
	 * @throws DaoException the dao exception
	 */
	@Override
	public List<Application> findUnposted() throws DaoException {
		List<Application> applications = new ArrayList<>();
		try (Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_FIND_UNPOSTED)) {
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Application application = buildApplication(resultSet);
				applications.add(application);
			}
		} catch (SQLException e) {
			logger.error("Problem at findUnpodted method at ApplicationDaoImpl", e);
			throw new DaoException("error ocurred at findUnpodted: ApplicationDaoImpl", e);
		}
		return applications;
	}

	/**
	 * Find posted.
	 *
	 * @return the list
	 * @throws DaoException the dao exception
	 */
	@Override
	public List<Application> findPosted() throws DaoException {
		List<Application> applications = new ArrayList<>();
		try (Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_FIND_POSTED)) {
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Application application = buildApplication(resultSet);
				applications.add(application);
			}
		} catch (SQLException e) {
			logger.error("Problem at findUnpodted method at ApplicationDaoImpl", e);
			throw new DaoException("error ocurred at findUnpodted: ApplicationDaoImpl", e);
		}
		return applications;
	}

	@Override
	public boolean deleteApplicationByApplicantId(int id) throws DaoException {
		int result = 0;

		try (Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_DELETE_BY_APPLICANTID)) {
			statement.setInt(1, id);
			result = statement.executeUpdate();
		} catch (SQLException e) {
			logger.error("Problem at deleteByApplicantId method at ApplicationDaoImpl", e);
			throw new DaoException("error ocured during deletion entity at ApplicatonDaoImpl", e);
		}
		return result > 0;
	}

	@Override
	public List<Application> findUnpostedByOrganizationName(Applicant applicant) throws DaoException {
		List<Application> applicationAtFindByOrganizationName = new ArrayList<>();
		String name = applicant.getOrganizationName();
		try (Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_FIND_UNPOSTED_BY_APPLICANT)) {
			statement.setString(1, name);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Application application = buildApplication(resultSet);
				applicationAtFindByOrganizationName.add(application);
			}
		} catch (SQLException e) {
			logger.error("Problem at findByOrganizationName method at ApplicationDaoImpl", e);
			throw new DaoException("problem at ApplicationDao: findById", e);
		}
		return applicationAtFindByOrganizationName;
	}
	
	@Override
	public List<Application> findPostedByOrganizationName(Applicant applicant) throws DaoException {
		List<Application> applicationAtFindByOrganizationName = new ArrayList<>();
		String name = applicant.getOrganizationName();
		try (Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_FIND_POSTED_BY_APPLICANT)) {
			statement.setString(1, name);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Application application = buildApplication(resultSet);
				applicationAtFindByOrganizationName.add(application);
			}
		} catch (SQLException e) {
			logger.error("Problem at findByOrganizationName method at ApplicationDaoImpl", e);
			throw new DaoException("problem at ApplicationDao: findById", e);
		}
		return applicationAtFindByOrganizationName;
	}
	
	/**
	 * Builds the application.
	 *
	 * @param resultSet the result set
	 * @return the application entity
	 * @throws SQLException the SQL exception
	 */
	private Application buildApplication(ResultSet resultSet) throws SQLException {
		Application application = new Application.ApplicationBuilder()
				.setApplicationId(resultSet.getInt(APPLICATION_ID)).setStatusId(resultSet.getInt(FK_STATUS_ID))
				.setApplicantId(resultSet.getInt(FK_APPLICANT_ID)).setTypeId(resultSet.getInt(FK_TYPE_ID))
				.setPhoto(resultSet.getString(PHOTO)).setName(resultSet.getString(NAME))
				.setSurname(resultSet.getString(SURNAME)).setTraits(resultSet.getString(TRAITS))
				.setWeight(resultSet.getInt(WEIGHT)).setHeight(resultSet.getInt(HEIGHT))
				.setDescription(resultSet.getString(DESCRIPTION)).setReward(resultSet.getInt(REWARD))
				.setExpirationDate(resultSet.getDate(EXPIRATION_DATE)).build();
		return application;
	}

	

	

}
