package org.uem.dam.gestor_farmacia.control.subcontrol;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import org.uem.dam.gestor_farmacia.control.MainController;
import org.uem.dam.gestor_farmacia.view.submenus.data_view.list_perspective.data_panel.UpdateDataDefaultPanel;

public abstract class UpdateItemPanelControl<T extends UpdateDataDefaultPanel> extends DefaultSubcontrol
		implements ActionListener {

	protected T dataPanel;

	public UpdateItemPanelControl(MainController mainController,
			T dataPanel) {
		super(mainController);
		this.dataPanel = dataPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton emisor = (JButton) e.getSource();
		if (emisor.getName().equals(UpdateDataDefaultPanel.NAME_BTN_UPDATE)) {
			onUpdateAction();
		} else if (emisor.getName().equals(UpdateDataDefaultPanel.NAME_BTN_REMOVE)) {
			onRemoveAction();
		}
	}

	public abstract void onUpdateAction();

	public abstract void onRemoveAction();

}
