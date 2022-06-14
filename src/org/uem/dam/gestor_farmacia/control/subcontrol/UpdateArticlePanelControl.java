package org.uem.dam.gestor_farmacia.control.subcontrol;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import org.uem.dam.gestor_farmacia.contract.ArticleContract;
import org.uem.dam.gestor_farmacia.contract.TableContract;
import org.uem.dam.gestor_farmacia.control.MainController;
import org.uem.dam.gestor_farmacia.model.Article;
import org.uem.dam.gestor_farmacia.utils.ContractUtils;
import org.uem.dam.gestor_farmacia.utils.SQLQueryBuilder;
import org.uem.dam.gestor_farmacia.utils.WindowActionUtils;
import org.uem.dam.gestor_farmacia.view.submenus.data_view.list_perspective.data_panel.ArticleDataPanel;

public class UpdateArticlePanelControl extends UpdateItemPanelControl<ArticleDataPanel> {

	public UpdateArticlePanelControl(MainController mainController,
			ArticleDataPanel dataPanel) {
		super(mainController, dataPanel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Article article = dataPanel.getInputItem();
		int result = persistence.executeUpdate((con, pstmt) -> {
			String query = SQLQueryBuilder.buildUpdateQuery(
					TableContract.ARTICLES.toString(),
					ContractUtils.getAllCols(ArticleContract.class),
					"AID"
			);
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, article.articleId());
			pstmt.setInt(2, article.providerId());
			pstmt.setString(3, article.name());
			pstmt.setDouble(4, article.price());
			pstmt.setInt(5, article.stock());
			pstmt.setInt(6, article.articleId());
			return pstmt;
		});
		WindowActionUtils.promptInfoDialog(
				mainFrame,
				String.format("%s item has been updated sucessfully", result),
				JOptionPane.INFORMATION_MESSAGE
		);
	}

}
