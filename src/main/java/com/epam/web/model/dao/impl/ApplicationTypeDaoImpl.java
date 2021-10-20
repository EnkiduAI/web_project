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

public class ApplicationTypeDaoImpl implements ApplicationTypeDao{
	private static final Logger logger = LogManager.getLogger();
	private static ApplicationTypeDaoImpl instance = new ApplicationTypeDaoImpl();
	private ConnectionPool connectionPool = ConnectionPool.getInstance();
	
	private ApplicationTypeDaoImpl() {
		
	}
	
	public static ApplicationTypeDaoImpl getInstance() {
		return instance;
	}
	private static final String SQL_SELECT_BY_TYPE = """
			select typeId, type
			from application_types
			where type = ?
			""";
	private static final String SQL_SELECT_ALL = """ 
			select typeId, type 
			from application_types
			""";
	private static final String SQL_DELETE_BY_ID = """ 
			delete from application_types where typeId = ?
			""";
	private static final String SQL_FIND_BY_ID = """
			select typeId, type from application_types
			where typeId = ?
			""";
	private static final String SQL_INSERT_TYPE = """
			insert into application_types(typeId, type) values(NULL,?)
			""";
	private static final String SQL_UPDATE_TYPE = """
			update application_types set type = ?
			where typeId = ?
			""";
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

	@Override
	public boolean delete(ApplicationType t) throws DaoException {
		int result = 0;
		try (Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_DELETE_BY_ID)){
			statement.setInt(1, t.getId());
			result = statement.executeUpdate();
		}catch(SQLException e) {
			logger.error("Problem at delete(ENTITY) method at ApplicationTypeDaoImpl", e);
			throw new DaoException("error ocured during deletion entity at ApplicatonTypeDaoImpl",e);
		}
		return result>0;
	}

	@Override
	public boolean create(ApplicationType t) throws DaoException {
		int result = 0;
		try (Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_INSERT_TYPE)){
			statement.setString(1, t.getType());
			result = statement.executeUpdate();
		}catch(SQLException e) {
			logger.error("Problem at create method at ApplicationTypeDaoImpl", e);
			throw new DaoException("creation failed at ApplicationTypeDaoImpl", e);
		}
		return result>0;
	}

	@Override
	public void update(ApplicationType t) throws DaoException {
		ApplicationType type = new ApplicationType();
		type = findById(t.getId());
		try(Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_TYPE)) {	
			statement.setString(1, t.getType());
			statement.executeUpdate();
		}catch(SQLException e) {
			logger.error("Problem at update method at ApplicationTypeDaoImpl", e);
			throw new DaoException("error occured during update at ApplicantDaoImpl");
		}
		
	}

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
private ApplicationType buildType(ResultSet resultSet) throws SQLException{
	ApplicationType type = new ApplicationType.TypeBuilder()
			.setId(resultSet.getInt(TYPE_ID))
			.setType(resultSet.getString(TYPE))
			.build();
	return type;
}
}
	

