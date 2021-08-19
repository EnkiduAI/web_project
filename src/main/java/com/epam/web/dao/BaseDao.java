package com.epam.web.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import com.epam.web.entity.Entity;
import com.epam.web.exception.DaoException;

public interface BaseDao<K,T extends Entity> {
	
 List<T> findAll() throws DaoException;
 T findById(K id);
 boolean delete(K id);
 boolean delete(T t);
 boolean create(T t);
 T update (T t);
 
 default void close(Statement statement) throws DaoException {
	 
	 try {
		 if (statement != null) {
			 statement.close();
		 }
		 }catch(SQLException e) {
			 throw new DaoException("BaseDao error on closing statement",e);
		 }
	 }
 
 default void close(Connection connection) throws DaoException {
	 try {
		 if(connection != null) {
			 connection.close();
		 }
		 }catch(SQLException e) {
			 throw new DaoException("BaseDao error on closing connection",e);
		 }
	 }
 }
 

 