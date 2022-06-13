package org.uem.dam.gestor_farmacia.control.subcontrol;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.uem.dam.gestor_farmacia.control.MainController;
import org.uem.dam.gestor_farmacia.view.submenus.data_view.list_perspective.data_panel.UpdateDataDefaultPanel;

public class UpdateItemPanelControl<T extends UpdateDataDefaultPanel> extends DefaultSubcontrol
		implements ActionListener {

	public UpdateItemPanelControl(MainController mainController) {
		super(mainController);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (T.ACTION_UPDATE.equals(e.getActionCommand())) {

			return;
		}

		System.err.println(String.format("UpdateItemPanelControl not recognized %s", e.getActionCommand()));

	}

}
