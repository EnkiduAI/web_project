package com.epam.web.connection;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class ConnectionFactory {
	private static final Properties properties = new Properties();
	private static String URL ="";
	private static final String DB_URL = "db.url";
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
			logger.error("ClassNotFound at ConnectionFactory", e)
			e.printStackTrace();
		}catch(IOException e) {
			logger.error("IOException at ConnectionFactory", e)
			e.printStackTrace();
		}
		
		URL = (String)properties.get(DB_URL);
	}

public Connection createConnection() throws SQLException{
	return DriverManager.getConnection(URL, properties);
}

}
