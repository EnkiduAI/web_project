package com.epam.web.model.connection;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConnectionFactory {
	private static final Properties properties = new Properties();
	private static final Logger logger = LogManager.getLogger();
	private static String URL ="";
	private static final String DB_URL = "url";
	private static final String RESOURCE = "database.properties";
	private static final String DB_DRIVER = "driver";

	static {
		String driverName = "";
		try {
			InputStream inputStream = ConnectionFactory.class.getClassLoader().getResourceAsStream(RESOURCE);
			properties.load(inputStream);
			driverName = (String)properties.get(DB_DRIVER);
			Class.forName(driverName);
		}catch(ClassNotFoundException e) {
			logger.fatal("Register driver fatal error");
			throw new ExceptionInInitializerError("Register driver fatal error");
		}catch(IOException e) {
			logger.fatal("Properties file can't be read");
			throw new ExceptionInInitializerError("Properties file can't be read");
		}
		if(URL != null) {
		URL = (String)properties.get(DB_URL);
		}else {
			logger.fatal("Missing database url");
			throw new ExceptionInInitializerError("Missing database url");
		}
	}

public static Connection createConnection() throws SQLException{
	Connection connection = DriverManager.getConnection(URL, properties); 
	return new ProxyConnection(connection);
}

}
