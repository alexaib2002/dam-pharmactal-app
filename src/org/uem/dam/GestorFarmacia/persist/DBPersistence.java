package org.uem.dam.GestorFarmacia.persist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.uem.dam.GestorFarmacia.contract.TableContract;
import org.uem.dam.GestorFarmacia.contract.UsersContract;
import org.uem.dam.GestorFarmacia.model.Article;
import org.uem.dam.GestorFarmacia.model.DBItem;
import org.uem.dam.GestorFarmacia.model.Medicine;
import org.uem.dam.GestorFarmacia.model.Provider;
import org.uem.dam.GestorFarmacia.model.SystemUser;
import org.uem.dam.GestorFarmacia.utils.SQLQueryBuilder;



public class DBPersistence {
	
	private DBConnection dbConnection;
	
	public DBPersistence() {
		dbConnection = new DBConnection();
	}
	
	public int executeUpdate(UpdateExpression expr) {
		int result = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = dbConnection.getConnection();
			pstmt = expr.executeUpdateSQL(con, pstmt);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error en codigo SQL");
		} finally {
			closeStatement(pstmt, con);
		}
		return result;
	}
	
	/* Method deprecated  */
	@Deprecated
	public ArrayList<SystemUser> getUsersList() {
		ArrayList<SystemUser> users = new ArrayList<>();
		
		ResultSet rset = null;
		Connection con = null;
		Statement stmt = null;
		
		try {
			con = dbConnection.getConnection();
			stmt = con.createStatement();
			String query = SQLQueryBuilder.buildSelectQuery(TableContract.USERS.toString(),
					UsersContract.getAllCols(), null, UsersContract.UID.toString(), false);
			rset = stmt.executeQuery(query);
			
			while (rset.next()) {
				users.add(new SystemUser(
						rset.getInt(UsersContract.UID.toString()),
						rset.getString(UsersContract.USERNAME.toString()), 
						rset.getString(UsersContract.PSSWD.toString()),
						rset.getBoolean(UsersContract.PERMISSION.toString())));
			}
			
		} catch (Exception e) {
			System.err.println("Excepcion desconocida");
			e.printStackTrace();
		} finally {
			try {
				if (rset != null)
					rset.close();
				if (stmt != null)
					stmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				System.out.println("Excepcion en codigo SQL");
			}
		}
		
		return users;
	}
	
	public ArrayList<DBItem> executeSelectArticles(SelectExpression expr, int colCount) {
		ArrayList<DBItem> result = new ArrayList<>();
		for (Object[] columnValues : executeSelect(expr, colCount)) {
			result.add(new Article(
					(int) columnValues[0], 
					(int) columnValues[1], 
					(String) columnValues[2], 
					(double) columnValues[3], 
					(int) columnValues[4]
			));
		}
		return result;
	}
	
	public ArrayList<DBItem> executeSelectMeds(SelectExpression expr, int colCount) {
		ArrayList<DBItem> result = new ArrayList<>();
		for (Object[] columnValues : executeSelect(expr, colCount)) {
			result.add(new Medicine(
					(int) columnValues[0], 
					(int) columnValues[1], 
					(int) columnValues[2], 
					(String) columnValues[3], 
					(int) columnValues[4] == 1 
			));
		}
		return result;
	}
	
	public ArrayList<DBItem> executeSelectProviders(SelectExpression expr, int colCount) {
		ArrayList<DBItem> result = new ArrayList<>();
		for (Object[] columnValues : executeSelect(expr, colCount)) {
			result.add(new Provider(
					(int) columnValues[0],
					(String) columnValues[1],
					(String) columnValues[2],
					(String) columnValues[3]
			));
		}
		return result;
	}
	
	public ArrayList<SystemUser> executeSelectUser(SelectExpression expr, int colCount) {
		ArrayList<SystemUser> result = new ArrayList<>();
		for (Object[] columnValues : executeSelect(expr, colCount)) {
			result.add(new SystemUser(
					(int) columnValues[0], 
					(String) columnValues[1], 
					(String) columnValues[2], 
					(int) columnValues[3] == 1 // translate Integer from DDBB to Java Boolean
			));
		}
		return result;
	}
	
	private ArrayList<Object[]> executeSelect(SelectExpression expr, int colCount) {
		ArrayList<Object[]> result = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			con = dbConnection.getConnection();
			pstmt = expr.executeSelectSQL(con, pstmt);
			rset = pstmt.executeQuery();
			Object[] colValue;
			while (rset.next()) {
				colValue = new Object[colCount];
				for (int i = 0; i < colValue.length; i++) {
					colValue[i] = rset.getObject(i + 1);
				}
				result.add(colValue);
			}
		} catch (SQLException e) {
			System.err.println("Error en codigo SQL");
			e.printStackTrace();
		} finally {
			closeStatement(pstmt, con);
		}
		return result;
	}
	
	private void closeStatement(PreparedStatement pstmt, Connection con) {
		try {
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();
			System.out.println("Conexion a BBDD cerrada con exito");
		} catch (SQLException e) {
			System.out.println("Error durante cierre de conexion a BBDD");
		}
	}
}
