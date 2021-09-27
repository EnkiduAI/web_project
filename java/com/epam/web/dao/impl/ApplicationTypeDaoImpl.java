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
import com.epam.web.dao.ApplicationTypeDao;
import com.epam.web.entity.ApplicationTypeEntity;
import com.epam.web.exception.DaoException;
import static com.epam.web.dao.TableColumns.*;

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
	public ApplicationTypeEntity findById(Integer id) throws DaoException {
		ApplicationTypeEntity type = new ApplicationTypeEntity();
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
	public boolean delete(ApplicationTypeEntity t) throws DaoException {
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
	public boolean create(ApplicationTypeEntity t) throws DaoException {
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
	public ApplicationTypeEntity update(ApplicationTypeEntity t) throws DaoException {
		ApplicationTypeEntity type = new ApplicationTypeEntity();
		type = findById(t.getId());
		try(Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_TYPE)) {	
			statement.setString(1, t.getType());
			statement.executeUpdate();
		}catch(SQLException e) {
			logger.error("Problem at update method at ApplicationTypeDaoImpl", e);
			throw new DaoException("error occured during update at ApplicantDaoImpl");
		}
		return type;
	}

	@Override
	public List<ApplicationTypeEntity> findAll() throws DaoException {
		List<ApplicationTypeEntity> types = new ArrayList<>();
		try(Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL)) {
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				ApplicationTypeEntity type = new ApplicationTypeEntity();
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
	public List<ApplicationTypeEntity> findByType(String type) throws DaoException {
		List<ApplicationTypeEntity> typesToFind = new ArrayList<>();
		try(Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_TYPE)) {
			statement.setString(1, type);			
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				ApplicationTypeEntity typeToFind = new ApplicationTypeEntity();
				typeToFind = buildType(resultSet);			
				typesToFind.add(typeToFind);
			}
		}catch (SQLException e) {
			logger.error("Problem at findByType method at ApplicationTypeDaoImpl", e);
			throw new DaoException("cannot find type: caused find by type at ApplicationTypeDaoImpl", e);
		}
		return typesToFind;
	}
private ApplicationTypeEntity buildType(ResultSet resultSet) throws SQLException{
	ApplicationTypeEntity type = new ApplicationTypeEntity.TypeBuilder()
			.setId(resultSet.getInt(TYPE_ID))
			.setType(resultSet.getString(TYPE))
			.build();
	return type;
}
}
	

