package org.uem.dam.gestor_farmacia.persist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JWindow;

import org.uem.dam.gestor_farmacia.contract.ArticleContract;
import org.uem.dam.gestor_farmacia.model.Article;
import org.uem.dam.gestor_farmacia.model.DBItem;
import org.uem.dam.gestor_farmacia.model.Medicine;
import org.uem.dam.gestor_farmacia.model.Provider;
import org.uem.dam.gestor_farmacia.model.SystemUser;
import org.uem.dam.gestor_farmacia.utils.ContractUtils;
import org.uem.dam.gestor_farmacia.utils.ErrorUtils;
import org.uem.dam.gestor_farmacia.utils.SQLQueryBuilder;
import org.uem.dam.gestor_farmacia.utils.WindowActionUtils;

public class DBPersistence {

	private DBConnection dbConnection;

	public DBPersistence() {
		dbConnection = new DBConnection();
	}

	public int getTableMaxValue(String table, String incId) {
		try {
			int id = (int) fetchColumns((con, pstmt) -> {
				String query = String.format("SELECT MAX(%s) FROM %s", incId, table);
				pstmt = con.prepareStatement(query);
				return pstmt;
			}, 1).get(0)[0];
			return id;
		} catch (ClassCastException cce) {
			System.err.println(String.format("DDBB couldn't find the max value of %s on table %s", incId, table));
			return -1;
		}
	}

	public int executeUpdate(ExecutableExpression expr) {
		int result = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = dbConnection.getConnection();
			pstmt = expr.executeSQL(con, pstmt);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			WindowActionUtils.promptInfoDialog(
					new JFrame(),
					String.format("Error while inserting item on DDBB:\n%s", e.getMessage()),
					JOptionPane.ERROR_MESSAGE
			);
		} finally {
			closeStatement(pstmt, con);
		}
		return result;
	}

	public ArrayList<DBItem> executeSelect(ExecutableExpression expr, Class<? extends DBItem> itemClass, int colCount) {
		ArrayList<DBItem> result = new ArrayList<>();
		for (Object[] columnValues : fetchColumns(expr, colCount)) {
			if (itemClass.equals(Article.class)) {
				double price = 0d;
				try {
					price = (double) columnValues[3];
				} catch (ClassCastException cce) {
					price = (int) columnValues[3];
				}
				result.add(
						new Article(
								(int) columnValues[0],
								(int) columnValues[1],
								(String) columnValues[2],
								price,
								(int) columnValues[4]
						)
				);
			} else if (itemClass.equals(Medicine.class)) {
				String[] cols = ContractUtils.getAllCols(ArticleContract.class);
				Article foreignArticle = null;
				try {
					foreignArticle = (Article) executeSelect((con, pstmt) -> {
						int articleId = (int) columnValues[0];
						String query = SQLQueryBuilder.buildSelectQuery(
								"ARTICLES",
								cols,
								new String[] {
										"AID = ?" },
								null,
								true
						);
						pstmt = con.prepareStatement(query);
						pstmt.setInt(1, articleId);
						return pstmt;
					}, Article.class, cols.length).get(0);
				} catch (IndexOutOfBoundsException e) {
					WindowActionUtils.promptInfoDialog(
							new JWindow(),
							String.format("Couldn't match Medicine with MedicineID %s", columnValues[1]),
							JOptionPane.ERROR_MESSAGE
					);
					continue;
				}
				result.add(
						new Medicine(
								foreignArticle,
								(int) columnValues[1],
								(int) columnValues[2],
								(String) columnValues[3],
								(int) columnValues[4] == 1
						)
				);
			} else if (itemClass.equals(Provider.class)) {
				result.add(
						new Provider(
								(int) columnValues[0],
								(String) columnValues[1],
								(String) columnValues[2],
								(String) columnValues[3]
						)
				);
			} else if (itemClass.equals(SystemUser.class)) {
				result.add(
						new SystemUser(
								(int) columnValues[0],
								(String) columnValues[1],
								(String) columnValues[2],
								(int) columnValues[3] == 1 // translate Integer from DDBB to Java Boolean
						)
				);
			} else {
				ErrorUtils.onFatalErrorException(String.format("Couln't match %s passed to Select method", itemClass));
			}
		}
		return result;
	}

	private ArrayList<Object[]> fetchColumns(ExecutableExpression expr, int colCount) {
		ArrayList<Object[]> result = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			con = dbConnection.getConnection();
			pstmt = expr.executeSQL(con, pstmt);
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

		} catch (SQLException e) {

		}
	}
}
