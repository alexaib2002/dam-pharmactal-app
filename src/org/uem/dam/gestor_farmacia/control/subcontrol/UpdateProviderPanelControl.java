package org.uem.dam.gestor_farmacia.control.subcontrol;

import javax.swing.JOptionPane;

import org.uem.dam.gestor_farmacia.contract.ProviderContract;
import org.uem.dam.gestor_farmacia.contract.TableContract;
import org.uem.dam.gestor_farmacia.control.MainController;
import org.uem.dam.gestor_farmacia.model.Provider;
import org.uem.dam.gestor_farmacia.utils.ContractUtils;
import org.uem.dam.gestor_farmacia.utils.SQLQueryBuilder;
import org.uem.dam.gestor_farmacia.utils.WindowActionUtils;
import org.uem.dam.gestor_farmacia.view.submenus.data_view.list_perspective.data_panel.ProvidersDataPanel;

public class UpdateProviderPanelControl extends UpdateItemPanelControl<ProvidersDataPanel> {

	public UpdateProviderPanelControl(MainController mainController,
			ProvidersDataPanel dataPanel) {
		super(mainController, dataPanel);
	}

	@Override
	public void onUpdateAction() {
		Provider article = dataPanel.getInputItem();
		int result = persistence.executeUpdate((con, pstmt) -> {
			String query = SQLQueryBuilder.buildUpdateQuery(
					TableContract.PROVIDERS.toString(),
					ContractUtils.getAllCols(ProviderContract.class),
					"PID"
			);
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, article.providerId());
			pstmt.setString(2, article.name());
			pstmt.setString(3, article.phone());
			pstmt.setString(4, article.address());
			pstmt.setInt(5, article.providerId());
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
