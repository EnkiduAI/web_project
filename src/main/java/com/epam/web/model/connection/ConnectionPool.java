package com.epam.web.model.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConnectionPool {

	private static final Logger logger = LogManager.getLogger();
	private static final int DEFAULT_POOL_SIZE = 10;
	private static final AtomicBoolean isCreated = new AtomicBoolean(false);
	private static Lock lock = new ReentrantLock(true);
	private static ConnectionPool instance;
	private BlockingQueue<ProxyConnection> freeConnections;
	private BlockingQueue<ProxyConnection> unavalibleConnections;
	
	private ConnectionPool() {
		freeConnections = new LinkedBlockingQueue<>(DEFAULT_POOL_SIZE);
		unavalibleConnections = new LinkedBlockingQueue<>(DEFAULT_POOL_SIZE);
		for(int i = 0; i < DEFAULT_POOL_SIZE; i++) {
			try {
			Connection connection = ConnectionFactory.createConnection();
			ProxyConnection proxyConnection = new ProxyConnection(connection);
			freeConnections.add(proxyConnection);
			}catch(SQLException e) {
				logger.error("Problem on create connection");
			}
		}
		if(freeConnections.isEmpty()) {
			throw new IllegalArgumentException();
		}
	}

	public static ConnectionPool getInstance() {
		if(!isCreated.get()) {
			lock.lock();
			try {
			if(instance == null) {
				instance = new ConnectionPool();
				isCreated.getAndSet(true);
			}
			}finally {
				lock.unlock();
			}
		}
		logger.log(Level.INFO,"created instance");
		return instance;
	}
	
	public Connection getConnection() {
		ProxyConnection proxyConnection = null;
		try {
			proxyConnection = freeConnections.take();
			unavalibleConnections.put(proxyConnection);
		}catch(InterruptedException ie) {
			logger.error("connection take failure at GetConnection method", ie);
			Thread.currentThread().interrupt();
			
		}
		return proxyConnection;
	}
	
	 public boolean releaseConnection(Connection connection) {
		if (!(connection instanceof ProxyConnection)) {
			return false;
		}
		if(unavalibleConnections.remove(connection)) {
			try {
				freeConnections.put((ProxyConnection)connection);
			} catch (InterruptedException e) {
				logger.error("interrupted exception at releaseConnection;");
			}
			return true;
		}else {
			return false;
		}
		}
	
	
	public void killPool() throws SQLException {
		for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
			try {
			freeConnections.take().close();
			}catch(InterruptedException e) {
				logger.error("interrupted exception in method killPool");
				throw new SQLException(e);
			}
		}
	}
	
	public void deregisterDrivers() {
		DriverManager.getDrivers().asIterator().forEachRemaining(driver ->{
		try{
			DriverManager.deregisterDriver(driver);
		}catch(SQLException e){
			logger.error("Cannot deregister driver");
		}
		});
	}

}
