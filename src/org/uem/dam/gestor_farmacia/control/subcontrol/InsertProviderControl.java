package org.uem.dam.gestor_farmacia.control.subcontrol;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JOptionPane;

import org.uem.dam.gestor_farmacia.contract.ProviderContract;
import org.uem.dam.gestor_farmacia.contract.TableContract;
import org.uem.dam.gestor_farmacia.control.MainController;
import org.uem.dam.gestor_farmacia.model.Provider;
import org.uem.dam.gestor_farmacia.utils.ContractUtils;
import org.uem.dam.gestor_farmacia.utils.SQLQueryBuilder;
import org.uem.dam.gestor_farmacia.utils.WindowActionUtils;
import org.uem.dam.gestor_farmacia.view.submenus.insertion.InsertProviderPanel;

public class InsertProviderControl extends InteractableControl implements ActionListener, ItemGenerator<Provider> {

	private final InsertProviderPanel providerInsertPanel;

	public InsertProviderControl(MainController mainController,
			InsertProviderPanel articleInsertPanel) {
		super(mainController);
		this.providerInsertPanel = articleInsertPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		switch (action) {
		case InsertProviderPanel.ACTION_ADD: {
			Provider provider = providerInsertPanel.getInputItem();
			insertItem(provider);
			break;
		}
		case InsertProviderPanel.ACTION_CLEAR: {
			providerInsertPanel.clearFields();
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + action);
		}
	}

	@Override
	public void insertItem(Provider provider) {
		int result = persistence.executeUpdate((con, pstmt) -> {
			String[] providerCols = ContractUtils.getAllCols(ProviderContract.class);
			String[] cols = Arrays.copyOfRange(providerCols, 1, providerCols.length);

			String query = SQLQueryBuilder.buildInsertQuery(TableContract.PROVIDERS.toString(), cols);

			pstmt = con.prepareStatement(query);
			pstmt.setString(1, provider.name());
			pstmt.setString(2, provider.phone());
			pstmt.setString(3, provider.address());

			return pstmt;
		});

		if (result == 1) {
			WindowActionUtils.promptInfoDialog(mainFrame, "Sentencia ejecutada correctamente", JOptionPane.INFORMATION_MESSAGE);
		}
	}

}
