package org.uem.dam.GestorFarmacia.db;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBAccess {

	private String driver;
	private String url;
	
	public DBAccess() {
		Properties prop = new Properties();
		InputStream is = null;
		
		try {
			is = new FileInputStream("db/ConfigurationDB.properties");
			prop.load(is);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (is != null) {
					is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
			
		
	
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url);
		
		return con;
	}
}
