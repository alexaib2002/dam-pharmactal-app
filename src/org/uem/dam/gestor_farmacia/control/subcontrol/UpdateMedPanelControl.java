package org.uem.dam.gestor_farmacia.control.subcontrol;

import javax.swing.JOptionPane;

import org.uem.dam.gestor_farmacia.contract.ArticleContract;
import org.uem.dam.gestor_farmacia.contract.MedContract;
import org.uem.dam.gestor_farmacia.contract.TableContract;
import org.uem.dam.gestor_farmacia.control.MainController;
import org.uem.dam.gestor_farmacia.model.Article;
import org.uem.dam.gestor_farmacia.model.Medicine;
import org.uem.dam.gestor_farmacia.persist.sql_expressions.UpdateItem;
import org.uem.dam.gestor_farmacia.utils.ContractUtils;
import org.uem.dam.gestor_farmacia.utils.SQLQueryBuilder;
import org.uem.dam.gestor_farmacia.utils.WindowActionUtils;
import org.uem.dam.gestor_farmacia.view.submenus.data_view.list_perspective.data_panel.MedDataPanel;

public class UpdateMedPanelControl extends UpdateItemPanelControl<MedDataPanel> {

	public UpdateMedPanelControl(MainController mainController,
			MedDataPanel dataPanel) {
		super(mainController, dataPanel);
	}

	@Override
	public void onUpdateAction() {
		Medicine med = dataPanel.getInputItem();
		Article medArt = med.article();
		int medResult = persistence.executeUpdate((con, pstmt) -> {
			String query = SQLQueryBuilder.buildUpdateQuery(
					TableContract.MEDS.toString(),
					ContractUtils.getAllCols(MedContract.class),
					"AID"
					);
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, medArt.articleId());
			pstmt.setInt(2, med.medId());
			pstmt.setInt(3, med.mass());
			pstmt.setString(4, med.unit());
			pstmt.setBoolean(5, med.requiresPresc());
			pstmt.setInt(6, medArt.articleId());
			return pstmt;
		});
		int artResult = persistence.executeUpdate(new UpdateItem(medArt));
		WindowActionUtils.promptInfoDialog(
				mainFrame,
				String.format("%s item and %s med has been updated sucessfully", medResult, artResult),
				JOptionPane.INFORMATION_MESSAGE
				);
	}

	@Override
	public void onRemoveAction() {
		Medicine med = dataPanel.getInputItem();
		Article medArt = med.article();
		int result = persistence.executeUpdate((con, pstmt) -> {
			String query = SQLQueryBuilder.buildDeleteQuery(
					TableContract.MEDS.toString(),
					MedContract.MID.toString()
					);
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, med.medId());
			return pstmt;
		});
		result = persistence.executeUpdate((con, pstmt) -> {
			String query = SQLQueryBuilder.buildDeleteQuery(
					TableContract.ARTICLES.toString(),
					ArticleContract.AID.toString()
					);
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, medArt.articleId());
			return pstmt;
		});

	}

}
