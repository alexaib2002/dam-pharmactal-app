package org.uem.dam.gestor_farmacia.control.subcontrol;

import javax.swing.JOptionPane;

import org.uem.dam.gestor_farmacia.contract.ArticleContract;
import org.uem.dam.gestor_farmacia.contract.TableContract;
import org.uem.dam.gestor_farmacia.control.MainController;
import org.uem.dam.gestor_farmacia.model.Article;
import org.uem.dam.gestor_farmacia.persist.sql_expressions.UpdateItem;
import org.uem.dam.gestor_farmacia.utils.SQLQueryBuilder;
import org.uem.dam.gestor_farmacia.utils.WindowActionUtils;
import org.uem.dam.gestor_farmacia.view.submenus.data_view.list_perspective.data_panel.ArticleDataPanel;

public class UpdateArticlePanelControl extends UpdateItemPanelControl<ArticleDataPanel> {

	public UpdateArticlePanelControl(MainController mainController,
			ArticleDataPanel dataPanel) {
		super(mainController, dataPanel);
	}

	@Override
	public void onUpdateAction() {
		Article article = dataPanel.getInputItem();
		int result = persistence.executeUpdate(new UpdateItem(article));
		WindowActionUtils.promptInfoDialog(
				mainFrame,
				String.format("%s item has been updated sucessfully", result),
				JOptionPane.INFORMATION_MESSAGE
				);
	}

	@Override
	public void onRemoveAction() {
		Article article = dataPanel.getInputItem();
		int result = persistence.executeUpdate((con, pstmt) -> {
			String query = SQLQueryBuilder.buildDeleteQuery(
					TableContract.ARTICLES.toString(),
					ArticleContract.AID.toString()
					);
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, article.articleId());
			return pstmt;
		});
		WindowActionUtils.promptInfoDialog(
				mainFrame,
				String.format("%s item has been removed sucessfully", result),
				JOptionPane.INFORMATION_MESSAGE
				);
	}

}
