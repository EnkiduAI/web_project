package com.epam.web.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Queue;
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
	private BlockingQueue<Connection> freeConnections;
	
	
	private ConnectionPool(){
		freeConnections = new LinkedBlockingQueue<>(DEFAULT_POOL_SIZE);
		for(int i = 0; i < DEFAULT_POOL_SIZE; i++) {
			try {
			ConnectionFactory connectionFactory = new ConnectionFactory();
			ProxyConnection connection = new ProxyConnection(connectionFactory.createConnection());
			freeConnections.put(connection);
			}catch(SQLException e) {
				logger.error("Problem on create connection", e);
				e.printStackTrace();;
			}
		}
	}

	public static ConnectionPool getInstance() {
		if(isCreated.getAndSet(true)) {
			lock.lock();
			if(instance == null) {
				instance = new ConnectionPool();
			}
		}
		logger.log(Level.INFO,"created instance");
		return instance;
	}
	
	public Connection getConnection() {
		Connection connection = null;
		try {
			connection = freeConnections.take();
		}catch(InterruptedException e) {
			logger.error("connection take failure", e);
			
		}
		return connection;
	}
	
	public void releaseConnection(Connection connection) {
		if (connection != null) {
			freeConnections.put(connection);
		}
	}
	
	public void killPool() {
		for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
			try {
			freeConnections.take().close();
			}catch(InterruptedException e) {
				logger.error("interrupted exception in method killPool", e);
			}catch(SQLException e) {
				logger.error("SQLException in method killPool", e);
			}
		}
	}
	
	public void deregisterDrivers() {
		DriverManager.getDrivers().asIterator().forEachRemaining(driver ->{
		try{
			DriverManager.deregisterDriver(driver);
		}catch(SQLException e){
			logger.error("Cannot deregister driver", e);
		}
		});
	}

}
