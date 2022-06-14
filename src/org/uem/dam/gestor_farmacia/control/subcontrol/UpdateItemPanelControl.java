package org.uem.dam.gestor_farmacia.control.subcontrol;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	public abstract void actionPerformed(ActionEvent e);

}
