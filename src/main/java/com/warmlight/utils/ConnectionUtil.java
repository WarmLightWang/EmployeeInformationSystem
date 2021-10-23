package com.warmlight.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This class will access my database. The Connection class has a method called
 * getConnection() will return the database that I created for interaction in
 * this project.
 * 
 */
public class ConnectionUtil {

	public static Connection getConnection() throws SQLException {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Class object is not found??");
			e.printStackTrace();
		}

		String url = "jdbc:postgresql://javadevelopment.cv7rzy5mrckf.us-east-2.rds.amazonaws.com:7317/postgres?currentSchema=UsingAWS";
		String username = "postgres";
		String password = "password5432";

		return DriverManager.getConnection(url, username, password);
	}

}
