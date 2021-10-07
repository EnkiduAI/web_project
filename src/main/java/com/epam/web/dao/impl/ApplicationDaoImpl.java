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
import com.epam.web.dao.ApplicationDao;
import com.epam.web.entity.ApplicationEntity;
import com.epam.web.exception.DaoException;
import static com.epam.web.dao.TableColumns.*;

public class ApplicationDaoImpl implements ApplicationDao{
	private static final Logger logger = LogManager.getLogger();
	private static ApplicationDaoImpl instance = new ApplicationDaoImpl();
	private ConnectionPool connectionPool = ConnectionPool.getInstance();
	
	private ApplicationDaoImpl() {
		
	}
	
	public static ApplicationDaoImpl getInstance() {
		return instance;
	}
	
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
applications.height,
applications.weight,
applications.description,
applications.reward,
applications.expirationDate
FROM mydb.applications
join mydb.status as st on st.statusId = applications.statusId
where st.status = ?;
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

	@Override
	public List<ApplicationEntity> findAll() throws DaoException {
		List<ApplicationEntity> applications = new ArrayList<>();
		try(Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL)) {
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				ApplicationEntity application = buildApplication(resultSet);				
				applications.add(application);
			}
		}catch (SQLException e) {
			logger.error("Problem at findAll method at ApplicationDaoImpl", e);
			throw new DaoException("error ocurred at findAll: ApplicationDaoImpl", e);
		}
		return applications;
	}

	@Override
	public ApplicationEntity findById(Integer id) throws DaoException {
		ApplicationEntity applicationAtFindById = new ApplicationEntity();
		try(Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ID)) {			
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {	
				applicationAtFindById = buildApplication(resultSet);
			}	
			}catch(SQLException e) {
				logger.error("Problem at findById method at ApplicationDaoImpl", e);
				throw new DaoException("problem at ApplicationDao: findById", e);
			}	
		return applicationAtFindById;
	}

	@Override
	public boolean delete(Integer id) throws DaoException {
		int result = 0;
		try(Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_DELETE_BY_ID)) {
			statement.setInt(1, id);
			result = statement.executeUpdate();
		}catch(SQLException e) {
			logger.error("Problem at delete method at ApplicationDaoImpl", e);
			throw new DaoException("error occured during delete(ID) by id at ApplicationDaoImpl", e);
		}
		return result>0;
	}

	@Override
	public boolean delete(ApplicationEntity t) throws DaoException {
		int result = 0;
		
		try(Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_DELETE_BY_ID)) {
			statement.setInt(1, t.getApplicationId());
			result = statement.executeUpdate();
		}catch(SQLException e) {
			logger.error("Problem at delete(ENTITY) method at ApplicationDaoImpl", e);
			throw new DaoException("error ocured during deletion entity at ApplicatonDaoImpl",e);
		}
		return result>0;
	}

	@Override
	public boolean create(ApplicationEntity t) throws DaoException {
		int result = 0;
		try (Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_INSERT_APPLICATION)){
			statement.setInt(1, t.getApplicationId());
			statement.setInt(2, t.getStatusId());
			statement.setInt(3, t.getApplicantId());
			statement.setInt(4, t.getTypeId());
			statement.setString(5, t.getPhoto());
			statement.setString(6, t.getName());
			statement.setString(7, t.getSurname());
			statement.setString(8, t.getTraits());
			statement.setInt(9, t.getWeight());
			statement.setInt(10, t.getHeight());
			statement.setString(11, t.getDescription());
			statement.setInt(12, t.getReward());
			statement.setDate(13, t.getExpirationDate());
			result = statement.executeUpdate();
		}catch(SQLException e) {
			logger.error("Problem at create method at ApplicationDaoImpl", e);
			throw new DaoException("creation failed at ApplicantDaoImpl", e);
		}
		return result>0;
	}

	@Override
	public void update(ApplicationEntity t) throws DaoException {
		ApplicationEntity application = new ApplicationEntity();
		application = findById(t.getApplicationId());
		try (Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_APPLICATION)){			
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
			statement.executeUpdate();
		}catch(SQLException e) {
			logger.error("Problem at update method at ApplicationDaoImpl", e);
			throw new DaoException("update failed at ApplicantDaoImpl", e);
		}
		
	}

	@Override
	public List<ApplicationEntity> findbyStatus(String status) throws DaoException {
		List<ApplicationEntity> applicationAtFindByStatus = new ArrayList<>();
		try(Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_STATUS)) {			
			statement.setString(1, status);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				ApplicationEntity application = buildApplication(resultSet);
				applicationAtFindByStatus.add(application);
			}	
			}catch(SQLException e) {
				logger.error("Problem at findByStatus method at ApplicationDaoImpl", e);
				throw new DaoException("problem at ApplicationDao: findById", e);
			}	
		return applicationAtFindByStatus;
	}

	@Override
	public List<ApplicationEntity> findbyOrganizationName(int organizationName) throws DaoException {
		List<ApplicationEntity> applicationAtFindByOrganizationName = new ArrayList<>();
		try(Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ORGANIZATION_NAME)){			
			statement.setInt(1, organizationName);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				ApplicationEntity application = buildApplication(resultSet);
				applicationAtFindByOrganizationName.add(application);
			}	
			}catch(SQLException e) {
				logger.error("Problem at findByOrganizationName method at ApplicationDaoImpl", e);
				throw new DaoException("problem at ApplicationDao: findById", e);
			}	
		return applicationAtFindByOrganizationName;
	}

	@Override
	public List<ApplicationEntity> findUnposted() throws DaoException {
		List<ApplicationEntity> applications = new ArrayList<>();
		try(Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_FIND_UNPOSTED)) {
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				ApplicationEntity application = buildApplication(resultSet);				
				applications.add(application);
			}
		}catch (SQLException e) {
			logger.error("Problem at findUnpodted method at ApplicationDaoImpl", e);
			throw new DaoException("error ocurred at findUnpodted: ApplicationDaoImpl", e);
		}
		return applications;
	}
	
	private ApplicationEntity buildApplication(ResultSet resultSet) throws SQLException{
		ApplicationEntity application = new ApplicationEntity.ApplicationBuilder()
				.setApplicationId(resultSet.getInt(APPLICATION_ID))
				.setStatusId(resultSet.getInt(FK_STATUS_ID))
				.setApplicantId(resultSet.getInt(FK_APPLICANT_ID))
				.setTypeId(resultSet.getInt(FK_TYPE_ID))
				.setPhoto(resultSet.getString(PHOTO))
				.setName(resultSet.getString(NAME))
				.setSurname(resultSet.getString(SURNAME))
				.setTraits(resultSet.getString(TRAITS))
				.setWeight(resultSet.getInt(WEIGHT))
				.setHeight(resultSet.getInt(HEIGHT))
				.setDescription(resultSet.getString(DESCRIPTION))
				.setReward(resultSet.getInt(REWARD))
				.setExpirationDate(resultSet.getDate(EXPIRATION_DATE))
				.build();
		return application;
	}

	
}
