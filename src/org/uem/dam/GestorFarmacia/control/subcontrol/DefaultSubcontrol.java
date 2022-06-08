package org.uem.dam.GestorFarmacia.control.subcontrol;

import org.uem.dam.GestorFarmacia.control.MainController;
import org.uem.dam.GestorFarmacia.view.MainFrame;

public abstract class DefaultSubcontrol {

	protected MainController mainController;
	protected MainFrame mainFrame;

	public DefaultSubcontrol(MainController mainController) {
		this.mainController = mainController;
		this.mainFrame = mainController.getMainView();
	}

	public abstract void parseAction(String action);

}
