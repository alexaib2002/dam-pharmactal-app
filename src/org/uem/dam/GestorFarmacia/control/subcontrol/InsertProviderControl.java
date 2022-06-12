package org.uem.dam.GestorFarmacia.control.subcontrol;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.uem.dam.GestorFarmacia.control.MainController;
import org.uem.dam.GestorFarmacia.model.Provider;
import org.uem.dam.GestorFarmacia.view.submenus.insertion.ProviderInsertPanel;

public class InsertProviderControl extends InteractableControl implements ActionListener {

	private final ProviderInsertPanel providerInsertPanel;

	public InsertProviderControl(MainController mainController,
			ProviderInsertPanel articleInsertPanel) {
		super(mainController);
		this.providerInsertPanel = articleInsertPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO test functionality
		String action = e.getActionCommand();
		switch (action) {
		case ProviderInsertPanel.ACTION_ADD: {
			Provider provider = providerInsertPanel.getInputItem();
			System.out.println(provider);
		}
		case ProviderInsertPanel.ACTION_CLEAR: {
			providerInsertPanel.clearFields();
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + action);
		}
	}

}
