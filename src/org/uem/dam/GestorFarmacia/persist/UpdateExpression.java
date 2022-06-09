package org.uem.dam.GestorFarmacia.persist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@FunctionalInterface
public interface UpdateExpression {
	public PreparedStatement executeUpdateSQL(Connection con, PreparedStatement ptsmt) throws SQLException;
}
