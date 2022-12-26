package com.project;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connections {
	public Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/e_commerce?user=root&password=0123456789");

		} catch (Exception e) {
			System.out.println(e);
		}
		return con;
	}
}
