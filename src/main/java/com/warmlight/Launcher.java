package com.warmlight;

import java.sql.Connection;
import java.sql.SQLException;

import com.warmlight.models.Menu;
import com.warmlight.utils.ConnectionUtil;

/**
 * This class is to run the program and execute the user inputs
 *
 */
public class Launcher {

	public static void main(String[] args) {

		try (Connection cn = ConnectionUtil.getConnection()) {
			System.out.println("Database connection is successful!!\n");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Call the Menu class
		Menu m = new Menu();
		m.display();
	}
}
