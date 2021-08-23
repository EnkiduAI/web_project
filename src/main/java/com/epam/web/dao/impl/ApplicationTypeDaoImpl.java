package com.epam.web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.epam.web.connection.ConnectionFactory;
import com.epam.web.dao.ApplicationTypeDao;
import com.epam.web.entity.ApplicationTypeEntity;
import com.epam.web.exception.DaoException;

public class ApplicationTypeDaoImpl implements ApplicationTypeDao{
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
			update application_types set typeId = ?, type = ?
			where typeId = ?
			""";
	@Override
	public ApplicationTypeEntity findById(Integer id) throws DaoException {
		Connection connection = null;
		PreparedStatement statement = null;
		ApplicationTypeEntity type = new ApplicationTypeEntity();
		try {			
			connection = ConnectionFactory.createConnection();
			statement = connection.prepareStatement(SQL_FIND_BY_ID);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {	
				type.setId(resultSet.getInt("typeId"));
				type.setType(resultSet.getString("type"));
			}	
			}catch(SQLException e) {
				throw new DaoException("problem at Application type DAO: findById", e);
			}finally {
				close(statement);
				close(connection);
			}		
		return type;
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
			throw new DaoException("error occured during delete by id at ApplicationTypeDaoImpl", e);
		}finally {
			close(statement);
			close(connection);
		}
		return result>0;
	}

	@Override
	public boolean delete(ApplicationTypeEntity t) throws DaoException {
		int result = 0;
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = ConnectionFactory.createConnection();
			statement = connection.prepareStatement(SQL_DELETE_BY_ID);
			statement.setInt(1, t.getId());
			result = statement.executeUpdate();
		}catch(SQLException e) {
			throw new DaoException("error ocured during deletion entity at ApplicatonTypeDaoImpl",e);
		}finally {
			close(statement);
			close(connection);
		}
		return result>0;
	}

	@Override
	public boolean create(ApplicationTypeEntity t) throws DaoException {
		int result = 0;
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = ConnectionFactory.createConnection();
			statement = connection.prepareStatement(SQL_INSERT_TYPE);
			statement.setString(1, t.getType());
			result = statement.executeUpdate();
		}catch(SQLException e) {
			throw new DaoException("creation failed at ApplicationTypeDaoImpl", e);
		}finally {
			close(statement);
			close(connection);
		}
		return result>0;
	}

	@Override
	public ApplicationTypeEntity update(ApplicationTypeEntity t) throws DaoException {
		ApplicationTypeEntity type = new ApplicationTypeEntity();
		Connection connection = null;
		PreparedStatement statement = null;
		type = findById(t.getId());
		try {
			connection = ConnectionFactory.createConnection();
			statement = connection.prepareStatement(SQL_UPDATE_TYPE);			
			statement.setString(1, t.getType());
			statement.executeUpdate();
		}catch(SQLException e) {
			throw new DaoException("error occured during update at ApplicantDaoImpl");
		}finally {
			close(statement);
			close(connection);
		}
		return type;
	}

	@Override
	public List<ApplicationTypeEntity> findAll() throws DaoException {
		List<ApplicationTypeEntity> types = new ArrayList<>();
		Connection connection = null;
		Statement statement = null;
		try {
			connection = ConnectionFactory.createConnection();
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL);
			while(resultSet.next()) {
				ApplicationTypeEntity type = new ApplicationTypeEntity();
				type.setId(resultSet.getInt("typeId"));
				type.setType(resultSet.getString("type"));
				
				types.add(type);
			}
		}catch (SQLException e) {
			throw new DaoException(e);
		}finally {
			close(statement);
			close(connection);
		}
		return types;
	}

	@Override
	public List<ApplicationTypeEntity> findByType(String type) throws DaoException {
		List<ApplicationTypeEntity> typesToFind = new ArrayList<>();
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = ConnectionFactory.createConnection();
			statement = connection.prepareStatement(SQL_SELECT_BY_TYPE);
			statement.setString(1, type);			
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				ApplicationTypeEntity typeToFind = new ApplicationTypeEntity();
				typeToFind.setId(resultSet.getInt("typeId"));
				typeToFind.setType(resultSet.getString("type"));				
				typesToFind.add(typeToFind);
			}
		}catch (SQLException e) {
			throw new DaoException("cannot find type: caused find by type at ApplicationTypeDaoImpl", e);
		}finally {
			close(statement);
			close(connection);
		}
		return typesToFind;
	}

}
	

