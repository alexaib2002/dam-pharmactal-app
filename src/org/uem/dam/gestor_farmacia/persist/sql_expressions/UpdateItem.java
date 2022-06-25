package org.uem.dam.gestor_farmacia.persist.sql_expressions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.uem.dam.gestor_farmacia.contract.ArticleContract;
import org.uem.dam.gestor_farmacia.contract.TableContract;
import org.uem.dam.gestor_farmacia.model.Article;
import org.uem.dam.gestor_farmacia.persist.ExecutableExpression;
import org.uem.dam.gestor_farmacia.utils.ContractUtils;
import org.uem.dam.gestor_farmacia.utils.SQLQueryBuilder;

public class UpdateItem implements ExecutableExpression {

	private Article article;

	public UpdateItem(Article article) {
		this.article = article;
	}

	@Override
	public PreparedStatement executeSQL(Connection con, PreparedStatement pstmt) throws SQLException {
		String query = SQLQueryBuilder.buildUpdateQuery(
				TableContract.ARTICLES.toString(),
				ContractUtils.getAllCols(ArticleContract.class),
				ArticleContract.AID.toString()
				);
		pstmt = con.prepareStatement(query);
		pstmt.setInt(1, article.articleId());
		pstmt.setInt(2, article.providerId());
		pstmt.setString(3, article.name());
		pstmt.setDouble(4, article.price());
		pstmt.setInt(5, article.stock());
		pstmt.setInt(6, article.articleId());
		return pstmt;
	}

}
