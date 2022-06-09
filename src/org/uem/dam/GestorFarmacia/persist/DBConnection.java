package org.uem.dam.GestorFarmacia.persist;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.uem.dam.GestorFarmacia.utils.ErrorUtils;



public class DBConnection {

	private String driver;
	private String url;
	
	public DBConnection() {
		FileInputStream fileInputStream;
		try {
			fileInputStream = new FileInputStream("dbconfig.properties");
			Properties dbProperties = new Properties();
			dbProperties.load(fileInputStream);
			driver = dbProperties.getProperty("driver");
			url = dbProperties.getProperty("url");
		} catch (FileNotFoundException e) {
			ErrorUtils.onFatalErrorException("El fichero de configuración de la BBDD (dbconfig.properties)" 
					+ "no ha sido encontrado en la ruta del ejecutable.");
		} catch (IOException e) {
			ErrorUtils.onFatalErrorException("Se ha producido un fallo en la Entrada/Salida de la aplicación");
		}
	}
	
	public Connection getConnection() {
		try {
			Class.forName(driver);
			return DriverManager.getConnection(url);
		} catch (ClassNotFoundException e) {
			System.err.println("El driver o la URL no se han podido cargar correctamente en el gestor de conexiones");
		} catch (SQLException e) {
			System.err.println("Excepcion SQL al obtener la conexion a la BBDD");
		}
		return null;
	}
	
}
