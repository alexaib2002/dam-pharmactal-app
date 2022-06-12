package org.uem.dam.GestorFarmacia.control.subcontrol;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import org.uem.dam.GestorFarmacia.contract.ArticleContract;
import org.uem.dam.GestorFarmacia.contract.MedContract;
import org.uem.dam.GestorFarmacia.contract.TableContract;
import org.uem.dam.GestorFarmacia.control.MainController;
import org.uem.dam.GestorFarmacia.model.Article;
import org.uem.dam.GestorFarmacia.model.DBItem;
import org.uem.dam.GestorFarmacia.model.Medicine;
import org.uem.dam.GestorFarmacia.utils.ContractUtils;
import org.uem.dam.GestorFarmacia.utils.SQLQueryBuilder;
import org.uem.dam.GestorFarmacia.view.submenus.insertion.InsertArticlePanel;

public class InsertArticleControl extends DefaultSubcontrol implements ActionListener, ItemGenerator<DBItem> {

	private final InsertArticlePanel articleInsertPanel;

	public InsertArticleControl(MainController mainController,
			InsertArticlePanel articleInsertPanel) {
		super(mainController);
		this.articleInsertPanel = articleInsertPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		switch (action) {
		case InsertArticlePanel.ACTION_ADD: {
			DBItem item = articleInsertPanel.getInputItem();
			insertItem(item);
			break;
		}
		case InsertArticlePanel.ACTION_CLEAR: {
			articleInsertPanel.clearFields();
			break;
		}
		case InsertArticlePanel.ACTION_YES: {
			articleInsertPanel.setVisibleMedElements(true);
			break;
		}
		case InsertArticlePanel.ACTION_NO: {
			articleInsertPanel.setVisibleMedElements(false);
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + action);
		}
	}

	@Override
	public void insertItem(DBItem item) {
		if (item instanceof Article) {

			Article article = (Article) item;

			int result = persistence.executeUpdate((con, pstmt) -> {
				String[] articleCols = ContractUtils.getAllCols(ArticleContract.class);
				String[] cols = Arrays.copyOfRange(articleCols, 1, articleCols.length);
				String query = SQLQueryBuilder.buildInsertQuery(TableContract.ARTICLES.toString(), cols);

				pstmt = con.prepareStatement(query);
				pstmt.setInt(1, article.providerId());
				pstmt.setString(2, article.name());
				pstmt.setDouble(3, article.price());
				pstmt.setInt(4, article.stock());

				return pstmt;
			});

		} else if (item instanceof Medicine) {

			Medicine medicine = (Medicine) item;
			Article article = medicine.article();

			// first, insert article data
			int result = persistence.executeUpdate((con, pstmt) -> {
				String[] articleCols = ContractUtils.getAllCols(ArticleContract.class);
				String[] cols = Arrays.copyOfRange(articleCols, 1, articleCols.length);
				String query = SQLQueryBuilder.buildInsertQuery(TableContract.ARTICLES.toString(), cols);

				pstmt = con.prepareStatement(query);
				pstmt.setInt(1, article.providerId());
				pstmt.setString(2, article.name());
				pstmt.setDouble(3, article.price());
				pstmt.setInt(4, article.stock());

				return pstmt;
			});

			result = persistence.executeUpdate((con, pstmt) -> {
				String[] cols = ContractUtils.getAllCols(MedContract.class);
				String query = SQLQueryBuilder.buildInsertQuery(TableContract.MEDS.toString(), cols);
				System.out.println(query);


				pstmt = con.prepareStatement(query);
				pstmt.setInt(1, persistence.getTableMaxValue(TableContract.ARTICLES.toString(), ArticleContract.AID.toString()));
				pstmt.setInt(2, medicine.medId());
				pstmt.setInt(3, medicine.mass());
				pstmt.setString(4, medicine.unit());
				pstmt.setBoolean(5, medicine.requiresPresc());

				return pstmt;
			});

		}
	}

}
