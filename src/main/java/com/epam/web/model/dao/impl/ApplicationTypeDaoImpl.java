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
import com.epam.web.model.dao.ApplicationTypeDao;
import com.epam.web.model.entity.ApplicationType;

// TODO: Auto-generated Javadoc
/**
 * The Class ApplicationTypeDaoImpl.
 */
public class ApplicationTypeDaoImpl implements ApplicationTypeDao{
	
	/** The Constant logger. */
	private static final Logger logger = LogManager.getLogger();
	
	/** The instance. */
	private static ApplicationTypeDaoImpl instance = new ApplicationTypeDaoImpl();
	
	/** The connection pool. */
	private ConnectionPool connectionPool = ConnectionPool.getInstance();
	
	/**
	 * Instantiates a new application type dao impl.
	 */
	private ApplicationTypeDaoImpl() {
		
	}
	
	/**
	 * Gets the single instance of ApplicationTypeDaoImpl.
	 *
	 * @return single instance of ApplicationTypeDaoImpl
	 */
	public static ApplicationTypeDaoImpl getInstance() {
		return instance;
	}
	
	/** The Constant SQL_SELECT_BY_TYPE. */
	private static final String SQL_SELECT_BY_TYPE = """
			select typeId, type
			from application_types
			where type = ?
			""";
	
	/** The Constant SQL_SELECT_ALL. */
	private static final String SQL_SELECT_ALL = """ 
			select typeId, type 
			from application_types
			""";
	
	/** The Constant SQL_DELETE_BY_ID. */
	private static final String SQL_DELETE_BY_ID = """ 
			delete from application_types where typeId = ?
			""";
	
	/** The Constant SQL_FIND_BY_ID. */
	private static final String SQL_FIND_BY_ID = """
			select typeId, type from application_types
			where typeId = ?
			""";
	
	/** The Constant SQL_INSERT_TYPE. */
	private static final String SQL_INSERT_TYPE = """
			insert into application_types(typeId, type) values(NULL,?)
			""";
	
	/** The Constant SQL_UPDATE_TYPE. */
	private static final String SQL_UPDATE_TYPE = """
			update application_types set type = ?
			where typeId = ?
			""";
	
	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the application type
	 * @throws DaoException the dao exception
	 */
	@Override
	public ApplicationType findById(Integer id) throws DaoException {
		ApplicationType type = new ApplicationType();
		try(Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ID)) {			
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {	
				type = buildType(resultSet);
			}	
			}catch(SQLException e) {
				logger.error("Problem at findById method at ApplicationTypeDaoImpl", e);
				throw new DaoException("problem at Application type DAO: findById", e);
			}	
		return type;
	}

	/**
	 * Delete type by id.
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
			logger.error("Problem at deleteById method at ApplicationTypeDaoImpl", e);
			throw new DaoException("error occured during delete by id at ApplicationTypeDaoImpl", e);
		}
		return result>0;
	}

	/**
	 * Delete type.
	 *
	 * @param t the t
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	@Override
	public boolean delete(ApplicationType type) throws DaoException {
		int result = 0;
		try (Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_DELETE_BY_ID)){
			statement.setInt(1, type.getId());
			result = statement.executeUpdate();
		}catch(SQLException e) {
			logger.error("Problem at delete(ENTITY) method at ApplicationTypeDaoImpl", e);
			throw new DaoException("error ocured during deletion entity at ApplicatonTypeDaoImpl",e);
		}
		return result>0;
	}

	/**
	 * Creates the type.
	 *
	 * @param t the t
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	@Override
	public boolean create(ApplicationType type) throws DaoException {
		int result = 0;
		try (Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_INSERT_TYPE)){
			statement.setString(1, type.getType());
			result = statement.executeUpdate();
		}catch(SQLException e) {
			logger.error("Problem at create method at ApplicationTypeDaoImpl", e);
			throw new DaoException("creation failed at ApplicationTypeDaoImpl", e);
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
	public void update(ApplicationType type) throws DaoException {
		type = findById(type.getId());
		try(Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_TYPE)) {	
			statement.setString(1, type.getType());
			statement.executeUpdate();
		}catch(SQLException e) {
			logger.error("Problem at update method at ApplicationTypeDaoImpl", e);
			throw new DaoException("error occured during update at ApplicantDaoImpl");
		}
		
	}

	/**
	 * Find all.
	 *
	 * @return the list
	 * @throws DaoException the dao exception
	 */
	@Override
	public List<ApplicationType> findAll() throws DaoException {
		List<ApplicationType> types = new ArrayList<>();
		try(Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL)) {
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				ApplicationType type = new ApplicationType();
				type = buildType(resultSet);			
				types.add(type);
			}
		}catch (SQLException e) {
			logger.error("Problem at findAll method at ApplicationTypeDaoImpl", e);
			throw new DaoException(e);
		}
		return types;
	}

	/**
	 * Find by type.
	 *
	 * @param type the type
	 * @return the list
	 * @throws DaoException the dao exception
	 */
	@Override
	public List<ApplicationType> findByType(String type) throws DaoException {
		List<ApplicationType> typesToFind = new ArrayList<>();
		try(Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_TYPE)) {
			statement.setString(1, type);			
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				ApplicationType typeToFind = new ApplicationType();
				typeToFind = buildType(resultSet);			
				typesToFind.add(typeToFind);
			}
		}catch (SQLException e) {
			logger.error("Problem at findByType method at ApplicationTypeDaoImpl", e);
			throw new DaoException("cannot find type: caused find by type at ApplicationTypeDaoImpl", e);
		}
		return typesToFind;
	}

/**
 * Builds the type.
 *
 * @param resultSet the result set
 * @return the application type
 * @throws SQLException the SQL exception
 */
private ApplicationType buildType(ResultSet resultSet) throws SQLException{
	return new ApplicationType.TypeBuilder()
			.setId(resultSet.getInt(TYPE_ID))
			.setType(resultSet.getString(TYPE))
			.build();
	
}
}
	

