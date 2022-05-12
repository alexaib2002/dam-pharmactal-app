package org.uem.dam.GestorFarmacia.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBAccess {

	private String driver;
	private String url;
	
	public DBAccess() {
		driver = "org.sqlite.JDBC";
		url = "jdbc:sqlite:db/ddbb_GestorFarmacia.db";
	}
	
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url);
		
		return con;
	}
}
