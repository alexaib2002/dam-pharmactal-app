package org.uem.dam.gestor_farmacia.control.subcontrol;

import javax.swing.JOptionPane;

import org.uem.dam.gestor_farmacia.contract.MedContract;
import org.uem.dam.gestor_farmacia.contract.TableContract;
import org.uem.dam.gestor_farmacia.control.MainController;
import org.uem.dam.gestor_farmacia.model.Medicine;
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
		Medicine article = dataPanel.getInputItem();
		int result = persistence.executeUpdate((con, pstmt) -> {
			String query = SQLQueryBuilder.buildUpdateQuery(
					TableContract.MEDS.toString(),
					ContractUtils.getAllCols(MedContract.class),
					"AID"
			);

			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, article.article().articleId());
			pstmt.setInt(2, article.medId());
			pstmt.setInt(3, article.mass());
			pstmt.setString(4, article.unit());
			pstmt.setBoolean(5, article.requiresPresc());
			pstmt.setInt(6, article.article().articleId());
			return pstmt;
		});
		WindowActionUtils.promptInfoDialog(
				mainFrame,
				String.format("%s item has been updated sucessfully", result),
				JOptionPane.INFORMATION_MESSAGE
		);
	}

	@Override
	public void onRemoveAction() {
		// TODO Auto-generated method stub

	}

}
